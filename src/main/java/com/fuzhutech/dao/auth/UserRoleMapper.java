package com.fuzhutech.dao.auth;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.auth.UserRole;

import java.util.HashMap;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    int insertUserWithRole(HashMap hashMap);

    int deleteUserWithRole(HashMap hashMap);
}