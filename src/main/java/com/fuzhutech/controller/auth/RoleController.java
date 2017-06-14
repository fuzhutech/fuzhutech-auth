package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.auth.Organization;
import com.fuzhutech.entity.auth.Resource;
import com.fuzhutech.entity.auth.Role;
import com.fuzhutech.service.auth.ResourceService;
import com.fuzhutech.service.auth.RoleResourceService;
import com.fuzhutech.service.auth.UserRoleService;
import com.fuzhutech.service.auth.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


//权限管理
@RestController
@RequestMapping("/roles")
public class RoleController extends AuthRestfulController<Role> {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    RoleResourceService roleResourceService;

    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
    public ResponseResult getUserList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int roleId) {
        ResponseResult responseResult = new ResponseResult();
        Role role = new Role();
        role.setId(roleId);
        try {
            responseResult.putData("sourceList", this.userService.queryNotWithRole(role));
            responseResult.putData("targetList", this.userService.queryWithRole(role));
            responseResult.setStatus(ResponseResult.SUCCESS);
            return responseResult;
        } catch (RuntimeException ex) {
            logger.error("getUserList失败：{}", ex);
            responseResult.setStatus(ResponseResult.FAILURE);
            responseResult.setMessage(ex.getMessage());
            return responseResult;
        }
    }

    /**
     * 编辑用户组织对应关系
     *
     * @param request        .
     * @param response       .
     * @param roleId .
     * @param userIds        注意双引号 "1,2,3"
     * @return .
     */
    @RequestMapping(value = "/{id}/users", method = RequestMethod.PUT)
    public ResponseResult editUserList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int roleId,
                                       @RequestBody String userIds) {
        try {
            //注意，需要额外处理字符串中的双引号
            int i = this.userRoleService.insertUserWithRole(userIds.replace("\"", ""), roleId);
            logger.info("i:", i);
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            logger.error("编辑失败：{}", ex);
            return new ResponseResult(ResponseResult.FAILURE, ex.getMessage());
        }
    }

    @RequestMapping(value = "/{id}/resources", method = RequestMethod.GET)
    public ResponseResult getResourceList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int roleId) {
        ResponseResult responseResult = new ResponseResult();
        Role role = new Role();
        role.setId(roleId);
        try {
            //responseResult.putData("sourceList", this.resourceService.queryNotWithRole(role));
            responseResult.putData("sourceList", this.resourceService.queryListByWhere(null));
            responseResult.putData("targetList", this.resourceService.queryWithRole(role));
            responseResult.setStatus(ResponseResult.SUCCESS);
            return responseResult;
        } catch (RuntimeException ex) {
            logger.error("getUserList失败：{}", ex);
            responseResult.setStatus(ResponseResult.FAILURE);
            responseResult.setMessage(ex.getMessage());
            return responseResult;
        }
    }

    /**
     * 编辑用户组织对应关系
     *
     * @param request        .
     * @param response       .
     * @param roleId .
     * @param resourceIds        注意双引号 "1,2,3"
     * @return .
     */
    @RequestMapping(value = "/{id}/resources", method = RequestMethod.PUT)
    public ResponseResult editResourceList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int roleId,
                                       @RequestBody String resourceIds) {
        try {
            //注意，需要额外处理字符串中的双引号
            int i = this.roleResourceService.insertResourceWithRole(resourceIds.replace("\"", ""), roleId);
            logger.info("i:", i);
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            logger.error("编辑失败：{}", ex);
            return new ResponseResult(ResponseResult.FAILURE, ex.getMessage());
        }
    }

}
