package com.fuzhutech.service.auth;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.Organization;
import com.fuzhutech.entity.auth.User;

import java.util.HashMap;
import java.util.List;

public interface UserService extends BaseService<User> {

    String createToken(User user);

    boolean checkToken(String token);

    /**
     * 根据Organization获取User
     * @param organization
     * @return List<User>
     */
    HashMap<String,Object> queryByOrganization(Organization organization);

}
