package com.fuzhutech.service.auth.impl;

import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.auth.PermissionMapper;
import com.fuzhutech.entity.auth.Permission;
import com.fuzhutech.service.auth.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Override
    public List<Permission> queryListByByUserId(Integer UserId) {
        return ((PermissionMapper)(this.mapper)).selectByUserId(UserId);
    }
}
