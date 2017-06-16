package com.fuzhutech.service.auth.impl;

import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.auth.PermissionMapper;
import com.fuzhutech.entity.auth.ChainPath;
import com.fuzhutech.entity.auth.Permission;
import com.fuzhutech.entity.auth.Resource;
import com.fuzhutech.service.auth.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Override
    public List<Permission> queryListByByUserId(Integer UserId) {
        return ((PermissionMapper)(this.mapper)).selectByUserId(UserId);
    }

    @Override
    public List<Permission> queryWithChainPath(ChainPath chainPath) {
        return ((PermissionMapper)(this.mapper)).selectWithChainPath(chainPath);
    }

    @Override
    public List<Permission> queryNotWithChainPath(ChainPath chainPath) {
        return ((PermissionMapper)(this.mapper)).selectNotWithChainPath(chainPath);
    }

    @Override
    public List<Permission> queryWithResource(Resource resource) {
        return ((PermissionMapper)(this.mapper)).selectWithResource(resource);
    }

    @Override
    public List<Permission> queryNotWithResource(Resource resource) {
        return ((PermissionMapper)(this.mapper)).selectNotWithResource(resource);
    }
}
