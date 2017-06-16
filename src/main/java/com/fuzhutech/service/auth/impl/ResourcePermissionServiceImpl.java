package com.fuzhutech.service.auth.impl;

import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.auth.ResourcePermissionMapper;
import com.fuzhutech.entity.auth.ResourcePermission;
import com.fuzhutech.service.auth.ResourcePermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ResourcePermissionServiceImpl extends BaseServiceImpl<ResourcePermission> implements ResourcePermissionService {

    @Override
    public int insertPermissionWithResource(String permIds, int resourceId) {
        String[] ids = permIds.split(",");
        List idList = new ArrayList();
        for (int i = 0; i < ids.length; i++) {
            if (StringUtils.isNumeric(ids[i]))
                idList.add(Integer.parseInt(ids[i]));
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("resourceId", resourceId);
        int result = 0;
        if (idList.size() > 0) {
            map.put("list", idList);
            result = ((ResourcePermissionMapper) this.mapper).insertPermissionWithResource(map);
        }

        return result + ((ResourcePermissionMapper) this.mapper).deletePermissionWithResource(map);
    }
}
