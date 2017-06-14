package com.fuzhutech.service.auth;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.RoleResource;

public interface RoleResourceService extends BaseService<RoleResource> {

    int insertResourceWithRole(String resourceIds,int roleId);

}
