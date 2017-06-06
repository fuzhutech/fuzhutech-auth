package com.fuzhutech.security.auth.shiro.spring.web;

import com.fuzhutech.entity.auth.ChainDefinition;
import com.fuzhutech.service.auth.ChainDefinitionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.CollectionUtils;
//import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShiroFilterFactoryBean extends org.apache.shiro.spring.web.ShiroFilterFactoryBean {

    private static final transient Logger log = LoggerFactory.getLogger(ShiroFilterFactoryBean.class);

    private SpringShiroFilter springShiroFilter;

    @Autowired
    private ChainDefinitionService chainDefinitionService;


    public ShiroFilterFactoryBean() {
        log.info("ShiroFilterFactoryBean is creating");
    }

    @PostConstruct
    public void init() {
        log.info("ShiroFilterFactoryBean init");
        chainDefinitionService.setShiroFilterFactoryBean(this);
    }

    //@Override
    public Map<String, String> getCustomFilterChainDefinitionMap() {
        log.info("ShiroFilterFactoryBean getFilterChainDefinitionMap");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();

        //List<ChainDefinition> list = chainDefinitionService.queryListByWhere(null);
        List<ChainDefinition> list = chainDefinitionService.queryAll();
        if (list == null || list.isEmpty())
            return filterChainDefinitionMap;

        String pathPattern = null;
        String filterChain = null;
        /*for (ChainDefinition chainDefinition : list) {
            pathPattern = chainDefinition.getPathPattern();
            filterChain = chainDefinition.getFilterChain();
            log.info("处理前PathPattern:{},FilterChain:{}", pathPattern, filterChain);

            filterChain = StringUtils.replaceEach(filterChain, new String[]{"0_[anon]","1_","2_"}, new String[]{"anon","role","perms"});
            String[] ary = StringUtils.split(filterChain,',');

            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < ary.length; i++){

                if(StringUtils.startsWith(ary[i].trim(),"0"))
                    continue;

                sb. append(ary[i].trim()+",");

            }
            String newStr = sb.toString();
            filterChain =newStr.substring(0, newStr.length() - 1);

            log.info("处理后pathPattern:{},FilterChain:{}", pathPattern,filterChain);
            filterChainDefinitionMap.put(pathPattern, filterChain);
        }*/

       /*
        filterChainDefinitionMap.put("/api/organizations","perms[auth:organization,auth:organization:action]");
        //mappedValue:[auth:organization, auth:organization:action] 只有前者为是，只有后着为否，两种都有为是

        //filterChainDefinitionMap.put("/api/organizations","perms[auth:organization],perms[auth:organization:action]");
        //[auth:organization:action] 只有前者为是，只有后者为是

        //filterChainDefinitionMap.put("/api/organizations","perms[auth:organization:action],perms[auth:organization]");
        //[auth:organization] 只有前者为否,只有前者为是

        //filterChainDefinitionMap.put("/api/organizations","perms[auth:organization:action]");
        //mappedValue:[auth:organization:action]  拥有[auth:organization]权限可以访问


        //---------------------------------------------------
        filterChainDefinitionMap.put("/api/organizations","perms[auth:organization1,auth:organization:action]");
        //mappedValue:[auth:organization1, auth:organization:action]，length:2， 只有前者为否，只有后着为否，两种都有为是

        filterChainDefinitionMap.put("/api/organizations","perms[auth:organization1],perms[auth:organization:action]");
        //[auth:organization:action] ,length:1,只有前者为否，只有后者为是，两者都有为真


        //-----------------------------------------------------
        filterChainDefinitionMap.put("/api/organizations","perms[auth:organization:*,auth:organization:action]");
        //mappedValue:[auth:organization:*, auth:organization:action] 只有前者为是，只有后着为否，两种都有为是

        filterChainDefinitionMap.put("/api/organizations","perms[auth:organization:*],perms[auth:organization:action]");
        //[auth:organization:action] 只有前者为是，只有后者为是

        filterChainDefinitionMap.put("/api/organizations","perms[auth:organization:action],perms[auth:organization:*]");
        //[auth:organization:*]只有前者为否,只有后者为是
        */

        //filterChainDefinitionMap.put("/api/organizations","rest[organization]");
        //get触发 [organization]

        //filterChainDefinitionMap.put("/api/organizations/*","rest[organization]");
        //get api/organizations 未触发
        //get api/organizations/1 [organization]

        //filterChainDefinitionMap.put("/api/organizations/","rest[organization]");
        //get api/organizations/1 未触发
        //get api/organizations/ 未触发
        //get api/organizations 未触发

        filterChainDefinitionMap.put("/api/organizations/**","rest[organization]");
        //get api/organizations 触发  mappedValue:[organization]
        //get api/organizations/ 触发
        //get api/organizations/1 触发
        //get api/organizations/1/2  触发  organization:read
        // Post organization:create

        filterChainDefinitionMap.put("/**","anon");


        log.info("wanbi");

        return filterChainDefinitionMap;
    }

    protected FilterChainManager createFilterChainManager() {

        log.info("ShiroFilterFactoryBean createFilterChainManager");

        FilterChainManager manager = super.createFilterChainManager();

        Map<String, String> chains = this.getCustomFilterChainDefinitionMap();
        if(!CollectionUtils.isEmpty(chains)) {
            Iterator var12 = chains.entrySet().iterator();

            while(var12.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry)var12.next();
                String url = (String)entry.getKey();
                String chainDefinition = (String)entry.getValue();
                manager.createChain(url, chainDefinition);
            }
        }

        return manager;
    }

    protected AbstractShiroFilter createInstance() throws Exception {
        log.info("Creating Shiro Filter instance.");
        SecurityManager securityManager = this.getSecurityManager();
        String msg;
        if (securityManager == null) {
            msg = "SecurityManager property must be set.";
            throw new BeanInitializationException(msg);
        } else if (!(securityManager instanceof WebSecurityManager)) {
            msg = "The security manager does not implement the WebSecurityManager interface.";
            throw new BeanInitializationException(msg);
        } else {
            org.apache.shiro.web.filter.mgt.FilterChainManager manager = this.createFilterChainManager();

            /*PathMatchingFilterChainResolver最重要的作用是：当请求url来的时候，他担任匹配工作（调用该类的getChain方法做匹配
            过滤执行doFilterInternal的时候，调用FilterChain resolved = resolver.getChain(request, response, origChain);*/
            PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
            chainResolver.setFilterChainManager(manager);

            springShiroFilter = new ShiroFilterFactoryBean.SpringShiroFilter((WebSecurityManager) securityManager, chainResolver);
            return springShiroFilter;
        }
    }

    //更换FilterChainManager,是否需要同步?
    public void ReplaceFilterChainManager() {
        log.info("ReplaceFilterChainManager");

        if (this.springShiroFilter == null) {
            return;
        }

        FilterChainManager manager = this.createFilterChainManager();
        PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
        chainResolver.setFilterChainManager(manager);

        springShiroFilter.setFilterChainResolver(chainResolver);
    }

    //extends OncePerRequestFilter
    private static final class SpringShiroFilter extends AbstractShiroFilter {

        protected SpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {

            if (webSecurityManager == null) {
                throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
            } else {
                this.setSecurityManager(webSecurityManager);
                if (resolver != null) {
                    this.setFilterChainResolver(resolver);
                }

            }
        }

    }
}
