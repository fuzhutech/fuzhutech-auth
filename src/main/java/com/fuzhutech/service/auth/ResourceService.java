package com.fuzhutech.service.auth;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.Resource;
import com.fuzhutech.entity.auth.Role;
import com.fuzhutech.entity.auth.User;

import java.util.List;

public interface ResourceService extends BaseService<Resource> {

    List<Resource> queryWithRole(Role role);

    List<Resource> queryNotWithRole(Role role);

    List<Resource> queryWithUser(User user);

    List<Resource> queryWithSystem(int sysId);

}
