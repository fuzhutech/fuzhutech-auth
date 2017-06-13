package com.fuzhutech.service.auth.impl;

import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.auth.UserOrganizationMapper;
import com.fuzhutech.entity.auth.UserOrganization;
import com.fuzhutech.service.auth.UserOrganizationService;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserOrganizationServiceImpl extends BaseServiceImpl<UserOrganization> implements UserOrganizationService {

    @Override
    public int insertUserInOrganization(String userIds, int organizationId) {
        int i = ((UserOrganizationMapper)this.mapper).deleteUserInOrganization(userIds,organizationId);
        return i + ((UserOrganizationMapper)this.mapper).insertUserInOrganization(userIds,organizationId);
    }
}
