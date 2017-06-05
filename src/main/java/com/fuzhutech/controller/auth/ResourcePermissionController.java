package com.fuzhutech.controller.auth;

import com.fuzhutech.entity.auth.ResourcePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 权限—资源管理
@RestController
@RequestMapping("/permission_resources")
public class ResourcePermissionController extends AuthRestfulController<ResourcePermission> {

  private static Logger logger = LoggerFactory.getLogger(ResourcePermissionController.class);

}
