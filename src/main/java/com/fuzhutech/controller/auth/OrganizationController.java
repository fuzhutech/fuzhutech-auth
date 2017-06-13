package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.auth.Organization;
import com.fuzhutech.entity.auth.User;
import com.fuzhutech.service.auth.UserOrganizationService;
import com.fuzhutech.service.auth.UserService;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

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
        Organization organization = new Organization();
        organization.setId(organizationId);
        return new ResponseResult(ResponseResult.SUCCESS,this.userService.queryByOrganization(organization));
    }

    /**
     * 编辑用户组织对应关系
     * @param request .
     * @param response .
     * @param organizationId .
     * @param userIds 注意双引号 "1,2,3"
     * @return .
     */
    @RequestMapping(value = "/{id}/users", method = RequestMethod.PUT)
    public ResponseResult editUserList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int organizationId,
                                       @RequestBody String  userIds) {
        try {
            //注意，需要额外处理字符串中的双引号
            int i = this.userOrganizationService.insertUserInOrganization(userIds.replace("\"",""),organizationId);
            logger.info("i:",i);
            return new ResponseResult(1);
        } catch (RuntimeException var5) {
            logger.error("编辑失败：{}", var5);
            return new ResponseResult(-1, (Object)null, var5.getMessage());
        }
    }

}
