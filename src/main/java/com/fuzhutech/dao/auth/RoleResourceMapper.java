package com.fuzhutech.dao.auth;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.auth.RoleResource;

import java.util.HashMap;

public interface RoleResourceMapper extends BaseMapper<RoleResource> {
    int insertResourceWithRole(HashMap hashMap);

    int deleteResourceWithRole(HashMap hashMap);
}