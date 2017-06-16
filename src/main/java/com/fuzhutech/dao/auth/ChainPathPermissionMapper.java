package com.fuzhutech.dao.auth;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.auth.ChainPathPermission;

import java.util.HashMap;

public interface ChainPathPermissionMapper extends BaseMapper<ChainPathPermission> {

    int insertPermissionWithChainPath(HashMap hashMap);

    int deletePermissionWithChainPath(HashMap hashMap);
}