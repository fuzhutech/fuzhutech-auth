package com.fuzhutech.service.auth.impl;

import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.auth.RoleResourceMapper;
import com.fuzhutech.entity.auth.RoleResource;
import com.fuzhutech.service.auth.RoleResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource> implements RoleResourceService {

    @Override
    public int insertResourceWithRole(String resourceIds, int roleId) {
        String[] ids = resourceIds.split(",");
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
            result = ((RoleResourceMapper) this.mapper).insertResourceWithRole(map);
        }

        return result + ((RoleResourceMapper) this.mapper).deleteResourceWithRole(map);
    }
}
