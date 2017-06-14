package com.fuzhutech.service.auth;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.Organization;
import com.fuzhutech.entity.auth.Role;
import com.fuzhutech.entity.auth.User;

import java.util.HashMap;
import java.util.List;

public interface UserService extends BaseService<User> {

    String createToken(User user);

    boolean checkToken(String token);

    List<User> queryInOrganization(Organization organization);

    List<User> queryNotInOrganization(Organization organization);

    List<User> queryWithRole(Role role);

    List<User> queryNotWithRole(Role role);

}
