package com.fuzhutech.dao.auth;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.auth.UserOrganization;

import java.util.HashMap;

public interface UserOrganizationMapper extends BaseMapper<UserOrganization> {

    int insertUserInOrganization(HashMap hashMap);

    int deleteUserInOrganization(HashMap hashMap);

}