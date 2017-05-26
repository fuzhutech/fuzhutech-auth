package com.fuzhutech.controller.auth;

import com.fuzhutech.entity.auth.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 部门管理
@RestController
@RequestMapping("/resources")
public class ResourceController extends AuthRestfulController<Resource> {

  private static Logger logger = LoggerFactory.getLogger(ResourceController.class);

}
