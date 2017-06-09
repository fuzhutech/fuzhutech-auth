package com.fuzhutech.controller.auth;

import com.fuzhutech.entity.auth.UserOrganization;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 用户
@RestController
@RequestMapping("/user-organizations")
public class UserOrganizationController extends AuthRestfulController<UserOrganization> {

}
