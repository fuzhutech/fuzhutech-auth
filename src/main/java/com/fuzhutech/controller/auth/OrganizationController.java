package com.fuzhutech.controller.auth;

import com.fuzhutech.entity.auth.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 部门管理
@RestController
@RequestMapping("/organizations")
public class OrganizationController extends AuthRestfulController<Organization> {

  private static Logger logger = LoggerFactory.getLogger(OrganizationController.class);

}
