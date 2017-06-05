package com.fuzhutech.controller.auth;

import com.fuzhutech.entity.auth.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 权限管理
@RestController
@RequestMapping("/permissions")
public class PermissionController extends AuthRestfulController<Permission> {

  private static Logger logger = LoggerFactory.getLogger(PermissionController.class);

}
