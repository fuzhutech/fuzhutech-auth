package com.fuzhutech.security.auth.shiro.realm;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fuzhutech.entity.auth.Permission;
import com.fuzhutech.entity.auth.User;
import com.fuzhutech.service.auth.PermissionService;
import com.fuzhutech.service.auth.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//shiro权限认证
public class ShiroDbRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /*public ShiroDbRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }*/

    /**
     * Shiro登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        logger.info("Shiro开始登录认证");

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        if (token.getUsername() == null) {
            throw new AccountException("用户名不能为空");
        }
        User record = new User();
        record.setLoginName(token.getUsername());
        //record.setPassword(token.getPassword().toString());
        //String hex = new Md5Hash(record.getPassword()).toHex();


        List<User> list = userService.queryListByWhere(record);
        // 账号不存在
        if (list == null || list.isEmpty()) {
            //return null;
            throw new UnknownAccountException("用户不存在");
        }

        User user = list.get(0);

        // 账号未启用
        /*if (user.getStatus() == 1) {
            return null;
        }*/

        // 读取用户的url和角色
        /*Map<String, Set<String>> resourceMap = roleService.selectResourceMapByUserId(user.getId());
        Set<String> urls = resourceMap.get("urls");
        Set<String> roles = resourceMap.get("roles");
        ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginName(), user.getName(), urls);
        shiroUser.setRoles(roles);*/
        ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginName(), user.getRealName(), null);
        // 认证缓存信息
        return new SimpleAuthenticationInfo(shiroUser, user.getPassword(), user.getLoginName());
        //其中把用户信息放入SimpleAuthenticationInfo对象，不能把整个user对象放入，不然会出现错误数组下标越界，在项目中user对象信息过于庞大，不能全部存入Cookie,Cookie对长度有一定的限制
    }

    //用户登录进行认证之前，先将该用户的其他session移除
    //实现单用户登录，一个用户同一时刻只能在一个地方登录
    private void test1(String userName) {
        //处理session
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        for (Session session : sessions) {
            //清除该用户以前登录时保存的session
            if (userName.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
                sessionManager.getSessionDAO().delete(session);
            }
        }
    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {

        logger.info("Shiro开始权限认证:");

        if (principals == null) {
            throw new AuthorizationException("Principal对象不能为空");
        }

        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();

        //获取用户响应的permission
        //List<String> permissions = CollectionUtils.extractToList(user.getResourcesList(), "permission",true);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();


        Set<String> roles = new HashSet<String>();
        Set<String> perms = new HashSet<String>();

        List<Permission> permissions = permissionService.queryListByByUserId(shiroUser.getId());
        for (Permission permission : permissions) {
            logger.info("permission code:{},type:{}",permission.getFilterChain(),permission.getFilterType());
            switch(permission.getFilterType()){
                case 1:roles.add(permission.getFilterChain());break;
                case 2:;perms.add(permission.getFilterChain());break;
            }
        }
        info.setRoles(roles);
        info.addStringPermissions(perms);

        //info.setRoles(shiroUser.getRoles());
        //info.addStringPermissions(shiroUser.getUrlSet());
        //info.addRole("admin");
        return info;
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        removeUserCache(shiroUser);
    }

    //清除用户缓存
    public void removeUserCache(ShiroUser shiroUser) {
        removeUserCache(shiroUser.getLoginName());
    }

    //清除用户缓存
    public void removeUserCache(String loginName) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection();
        principals.add(loginName, super.getName());
        super.clearCachedAuthenticationInfo(principals);
    }
}
