package com.fuzhutech.dao.auth;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.auth.UserOrganization;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserOrganizationMapper extends BaseMapper<UserOrganization> {

    //int insertUserInOrganization(@Param("userIds")List userIds, @Param("organizationId")int organizationId);

    //int deleteUserInOrganization(@Param("userIds")List userIds, @Param("organizationId")int organizationId);

    //int insertUserInOrganization(HashMap hashMap);

    //int deleteUserInOrganization(HashMap hashMap);

    int insertUserInOrganization(@Param("userIds")String userIds, @Param("organizationId")int organizationId);

    int deleteUserInOrganization(@Param("userIds")String userIds, @Param("organizationId")int organizationId);

}