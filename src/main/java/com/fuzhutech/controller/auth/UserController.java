package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.auth.Role;
import com.fuzhutech.entity.auth.User;
import com.fuzhutech.service.auth.ResourceService;
import com.fuzhutech.service.auth.RoleService;
import com.fuzhutech.service.auth.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 用户
@RestController
@RequestMapping("/users")
public class UserController extends AuthRestfulController<User> {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    ResourceService resourceService;

    @RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
    public ResponseResult getUserList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int userId) {
        ResponseResult responseResult = new ResponseResult();
        User user = new User();
        user.setId(userId);
        try {
            responseResult.putData("sourceList", this.roleService.queryNotWithUser(user));
            responseResult.putData("targetList", this.roleService.queryWithUser(user));
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
     * 编辑用户角色对应关系
     *
     * @param request        .
     * @param response       .
     * @param uerId .
     * @param roleIds        注意双引号 "1,2,3"
     * @return .
     */
    @RequestMapping(value = "/{id}/roles", method = RequestMethod.PUT)
    public ResponseResult editUserList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int uerId,
                                       @RequestBody String roleIds) {
        try {
            //注意，需要额外处理字符串中的双引号
            int i = this.userRoleService.insertRoleWithUser(roleIds.replace("\"", ""), uerId);
            logger.info("i:", i);
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            logger.error("编辑失败：{}", ex);
            return new ResponseResult(ResponseResult.FAILURE, ex.getMessage());
        }
    }

    @RequestMapping(value = "/{id}/resources", method = RequestMethod.GET)
    public ResponseResult getResourceList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int userId) {
        ResponseResult responseResult = new ResponseResult();
        User user = new User();
        user.setId(userId);
        try {
            responseResult.putData("list", this.resourceService.queryWithUser(user));
            responseResult.setStatus(ResponseResult.SUCCESS);
            return responseResult;
        } catch (RuntimeException ex) {
            logger.error("getUserList失败：{}", ex);
            responseResult.setStatus(ResponseResult.FAILURE);
            responseResult.setMessage(ex.getMessage());
            return responseResult;
        }
    }

}
