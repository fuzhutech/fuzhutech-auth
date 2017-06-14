package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.auth.Organization;
import com.fuzhutech.service.auth.UserOrganizationService;
import com.fuzhutech.service.auth.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 部门管理
@RestController
@RequestMapping("/organizations")
public class OrganizationController extends AuthRestfulController<Organization> {

    private static Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    UserService userService;

    @Autowired
    UserOrganizationService userOrganizationService;

    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
    public ResponseResult getUserList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int organizationId) {
        ResponseResult responseResult = new ResponseResult();
        Organization organization = new Organization();
        organization.setId(organizationId);
        try {
            responseResult.putData("sourceList", this.userService.queryNotInOrganization(organization));
            responseResult.putData("targetList", this.userService.queryInOrganization(organization));
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
     * @param organizationId .
     * @param userIds        注意双引号 "1,2,3"
     * @return .
     */
    @RequestMapping(value = "/{id}/users", method = RequestMethod.PUT)
    public ResponseResult editUserList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int organizationId,
                                       @RequestBody String userIds) {
        try {
            //注意，需要额外处理字符串中的双引号
            int i = this.userOrganizationService.insertUserInOrganization(userIds.replace("\"", ""), organizationId);
            logger.info("i:", i);
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            logger.error("编辑失败：{}", ex);
            return new ResponseResult(ResponseResult.FAILURE, ex.getMessage());
        }
    }

}
