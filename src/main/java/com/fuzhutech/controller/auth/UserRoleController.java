package com.fuzhutech.controller.auth;

import com.fuzhutech.entity.auth.UserRole;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 用户
@RestController
@RequestMapping("/user-roles")
public class UserRoleController extends AuthRestfulController<UserRole> {

}
