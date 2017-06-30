package com.fuzhutech.service.auth;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.ChainPath;
import com.fuzhutech.entity.auth.Permission;
import com.fuzhutech.entity.auth.Resource;

import java.util.List;

//部门管理
public interface PermissionService extends BaseService<Permission> {

    List<Permission> queryListByByUserId(Integer UserId);

    List<Permission> queryWithChainPath(ChainPath chainPath);

    List<Permission> queryNotWithChainPath(ChainPath chainPath);

    List<Permission> queryWithResource(Resource resource);

    List<Permission> queryNotWithResource(Resource resource);

    public Integer generateId(Permission record);
}
