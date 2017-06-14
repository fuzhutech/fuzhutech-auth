package com.fuzhutech.service.auth.impl;

import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.auth.ResourceMapper;
import com.fuzhutech.entity.auth.Resource;
import com.fuzhutech.entity.auth.Role;
import com.fuzhutech.service.auth.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

    @Override
    public List<Resource> queryWithRole(Role role) {
        return ((ResourceMapper) mapper).selectWithRole(role);
    }

    @Override
    public List<Resource> queryNotWithRole(Role role) {
        return ((ResourceMapper) mapper).selectNotWithRole(role);
    }
}
