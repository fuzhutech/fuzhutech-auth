package com.fuzhutech.service.auth.impl;

import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.auth.UserOrganizationMapper;
import com.fuzhutech.entity.auth.UserOrganization;
import com.fuzhutech.service.auth.UserOrganizationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserOrganizationServiceImpl extends BaseServiceImpl<UserOrganization> implements UserOrganizationService {

    private static Logger logger = LoggerFactory.getLogger(UserOrganizationServiceImpl.class);

    @Override
    public int insertUserInOrganization(String userIds, int organizationId) {

        String[] ids = userIds.split(",");
        List idList = new ArrayList();
        for (int i = 0; i < ids.length; i++) {
            if (StringUtils.isNumeric(ids[i]))
                idList.add(Integer.parseInt(ids[i]));
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("organizationId", organizationId);
        int result = 0;
        if (idList.size() > 0) {
            map.put("list", idList);
            result = ((UserOrganizationMapper) this.mapper).insertUserInOrganization(map);
        }

        return result + ((UserOrganizationMapper) this.mapper).deleteUserInOrganization(map);
    }
}
