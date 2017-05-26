package com.fuzhutech.service.auth;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.User;

public interface UserService extends BaseService<User> {

    String createToken(User user);

    boolean checkToken(String token);

}
