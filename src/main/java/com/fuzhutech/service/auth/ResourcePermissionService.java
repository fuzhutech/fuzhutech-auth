package com.fuzhutech.service.auth;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.ResourcePermission;

public interface ResourcePermissionService extends BaseService<ResourcePermission> {

    int insertPermissionWithResource(String permIds,int resourceId);
}
