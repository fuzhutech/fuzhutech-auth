package com.fuzhutech.dao.auth;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.auth.Role;
import com.fuzhutech.entity.auth.User;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectWithUser(User user);

    List<Role> selectNotWithUser(User user);
}