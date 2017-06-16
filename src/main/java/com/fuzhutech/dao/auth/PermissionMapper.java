package com.fuzhutech.dao.auth;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.auth.ChainPath;
import com.fuzhutech.entity.auth.Permission;
import com.fuzhutech.entity.auth.Resource;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> selectByUserId(Integer id);

    List<Permission> selectWithChainPath(ChainPath chainPath);

    List<Permission> selectNotWithChainPath(ChainPath chainPath);

    List<Permission> selectWithResource(Resource resource);

    List<Permission> selectNotWithResource(Resource resource);
}