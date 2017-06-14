package com.fuzhutech.dao.auth;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.auth.Organization;
import com.fuzhutech.entity.auth.Role;
import com.fuzhutech.entity.auth.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据Organization获取User
     * @param organization
     * @return List<User>
     */
    List<User> selectInOrganization(Organization organization);

    List<User> selectNotInOrganization(Organization organization);

    List<User> selectWithRole(Role role);

    List<User> selectNotWithRole(Role role);

}