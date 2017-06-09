package com.fuzhutech.controller.auth;

import com.fuzhutech.entity.auth.RoleResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//权限管理
@RestController
@RequestMapping("/role-resources")
public class RoleResourceController extends AuthRestfulController<RoleResource> {

    private static Logger logger = LoggerFactory.getLogger(RoleResourceController.class);

}
