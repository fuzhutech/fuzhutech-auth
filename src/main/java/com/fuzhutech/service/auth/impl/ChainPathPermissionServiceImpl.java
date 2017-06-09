package com.fuzhutech.service.auth.impl;


import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.entity.auth.ChainPathPermission;
import com.fuzhutech.service.auth.ChainPathPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ChainPathPermissionServiceImpl extends BaseServiceImpl<ChainPathPermission> implements ChainPathPermissionService {

    private static Logger logger = LoggerFactory.getLogger(ChainPathPermissionServiceImpl.class);

}
