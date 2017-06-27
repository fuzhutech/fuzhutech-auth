package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
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

// 系统
@RestController
@RequestMapping("/systems")
public class SystemController extends AuthRestfulController<User> {

    private static Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    ResourceService resourceService;

    @RequestMapping(value = "/{id}/resources", method = RequestMethod.GET)
    public ResponseResult getResourceList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int sysId) {
        ResponseResult responseResult = new ResponseResult();
        try {
            responseResult.putData("list", this.resourceService.queryWithSystem(sysId));
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
