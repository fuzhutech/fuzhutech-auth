package com.fuzhutech.controller.auth;

import com.fuzhutech.entity.auth.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//权限管理
@RestController
@RequestMapping("/roles")
public class RoleController extends AuthRestfulController<Role> {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

}
