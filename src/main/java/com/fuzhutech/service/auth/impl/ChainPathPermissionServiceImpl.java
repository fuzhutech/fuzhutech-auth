package com.fuzhutech.service.auth.impl;


import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.auth.ChainPathPermissionMapper;
import com.fuzhutech.entity.auth.ChainPathPermission;
import com.fuzhutech.service.auth.ChainPathPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ChainPathPermissionServiceImpl extends BaseServiceImpl<ChainPathPermission> implements ChainPathPermissionService {

    private static Logger logger = LoggerFactory.getLogger(ChainPathPermissionServiceImpl.class);

    @Override
    public int insertPermissionWithChainPath(String permIds, int chainPathId) {
        String[] ids = permIds.split(",");
        List idList = new ArrayList();
        for (int i = 0; i < ids.length; i++) {
            if (StringUtils.isNumeric(ids[i]))
                idList.add(Integer.parseInt(ids[i]));
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("chainPathId", chainPathId);
        int result = 0;
        if (idList.size() > 0) {
            map.put("list", idList);
            result = ((ChainPathPermissionMapper) this.mapper).insertPermissionWithChainPath(map);
        }

        return result + ((ChainPathPermissionMapper) this.mapper).deletePermissionWithChainPath(map);
    }
}
