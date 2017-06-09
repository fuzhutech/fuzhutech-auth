package com.fuzhutech.controller.auth;

import com.fuzhutech.entity.auth.RolePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//权限管理
@RestController
@RequestMapping("/role-permissions")
public class RolePermissionController extends AuthRestfulController<RolePermission> {

    private static Logger logger = LoggerFactory.getLogger(RolePermissionController.class);

}
