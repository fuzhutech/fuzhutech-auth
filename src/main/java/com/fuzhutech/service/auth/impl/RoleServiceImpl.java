package com.fuzhutech.service.auth.impl;

import com.fuzhutech.dao.auth.RoleMapper;
import com.fuzhutech.entity.auth.Role;
import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.entity.auth.User;
import com.fuzhutech.service.auth.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Override
    public List<Role> queryWithUser(User user) {
        return ((RoleMapper) mapper).selectWithUser(user);
    }

    @Override
    public List<Role> queryNotWithUser(User user) {
        return ((RoleMapper) mapper).selectNotWithUser(user);
    }
}
