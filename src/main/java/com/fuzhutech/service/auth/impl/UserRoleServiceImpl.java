package com.fuzhutech.service.auth.impl;

import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.auth.UserRoleMapper;
import com.fuzhutech.entity.auth.UserRole;
import com.fuzhutech.service.auth.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

    @Override
    public int insertUserWithRole(String userIds, int roleId) {
        String[] ids = userIds.split(",");
        List idList = new ArrayList();
        for (int i = 0; i < ids.length; i++) {
            if (StringUtils.isNumeric(ids[i]))
                idList.add(Integer.parseInt(ids[i]));
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleId);
        int result = 0;
        if (idList.size() > 0) {
            map.put("list", idList);
            result = ((UserRoleMapper) this.mapper).insertUserWithRole(map);
        }

        return result + ((UserRoleMapper) this.mapper).deleteUserWithRole(map);
    }

    @Override
    public int insertRoleWithUser(String roleIds, int userId) {
        String[] ids = roleIds.split(",");
        List idList = new ArrayList();
        for (int i = 0; i < ids.length; i++) {
            if (StringUtils.isNumeric(ids[i]))
                idList.add(Integer.parseInt(ids[i]));
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        int result = 0;
        if (idList.size() > 0) {
            map.put("list", idList);
            result = ((UserRoleMapper) this.mapper).insertRoleWithUser(map);
        }

        return result + ((UserRoleMapper) this.mapper).deleteRoleWithUser(map);
    }


}
