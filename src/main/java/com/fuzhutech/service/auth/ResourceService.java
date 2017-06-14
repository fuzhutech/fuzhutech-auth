package com.fuzhutech.service.auth;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.Resource;
import com.fuzhutech.entity.auth.Role;

import java.util.List;

public interface ResourceService extends BaseService<Resource> {

    List<Resource> queryWithRole(Role role);

    List<Resource> queryNotWithRole(Role role);

}
