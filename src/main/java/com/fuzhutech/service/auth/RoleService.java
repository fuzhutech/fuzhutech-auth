package com.fuzhutech.service.auth;

import com.fuzhutech.entity.auth.Role;
import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.User;

import java.util.List;
import java.util.Map;

// 权限管理
public interface RoleService extends BaseService<Role> {

    List<Role> queryWithUser(User user);

    List<Role> queryNotWithUser(User user);
}
