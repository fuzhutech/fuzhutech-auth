package com.fuzhutech.dao.auth;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.auth.Resource;
import com.fuzhutech.entity.auth.Role;
import com.fuzhutech.entity.auth.User;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource> {

    List<Resource> selectWithRole(Role role);

    List<Resource> selectNotWithRole(Role role);

    List<Resource> selectWithUser(User user);

    List<Resource> selectWithSystem(int sysId);

    Integer selectLastInsertId(Resource resource);
}