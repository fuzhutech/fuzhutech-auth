package com.fuzhutech.service.auth;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.ChainPathPermission;

public interface ChainPathPermissionService extends BaseService<ChainPathPermission> {

    int insertPermissionWithChainPath(String permIds,int chainPathId);
}
