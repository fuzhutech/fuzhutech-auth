package com.fuzhutech.controller.auth;

import com.fuzhutech.entity.auth.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 用户
@RestController
@RequestMapping("/users")
public class UserController extends AuthRestfulController<User> {

}
