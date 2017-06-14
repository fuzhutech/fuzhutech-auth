package com.fuzhutech.service.auth;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.UserRole;

// 权限管理
public interface UserRoleService extends BaseService<UserRole> {

    int insertUserWithRole(String userIds,int organizationId);

}
