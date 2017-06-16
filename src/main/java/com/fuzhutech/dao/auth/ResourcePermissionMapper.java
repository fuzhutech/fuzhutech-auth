package com.fuzhutech.dao.auth;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.auth.ResourcePermission;

import java.util.HashMap;

public interface ResourcePermissionMapper extends BaseMapper<ResourcePermission> {

    int insertPermissionWithResource(HashMap hashMap);

    int deletePermissionWithResource(HashMap hashMap);
}