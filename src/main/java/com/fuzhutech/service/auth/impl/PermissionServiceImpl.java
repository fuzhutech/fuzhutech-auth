package com.fuzhutech.service.auth.impl;

import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.auth.PermissionMapper;
import com.fuzhutech.entity.auth.ChainPath;
import com.fuzhutech.entity.auth.Permission;
import com.fuzhutech.entity.auth.Resource;
import com.fuzhutech.service.auth.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    private static Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Override
    public List<Permission> queryListByByUserId(Integer UserId) {
        return ((PermissionMapper)(this.mapper)).selectByUserId(UserId);
    }

    @Override
    public List<Permission> queryWithChainPath(ChainPath chainPath) {
        return ((PermissionMapper)(this.mapper)).selectWithChainPath(chainPath);
    }

    @Override
    public List<Permission> queryNotWithChainPath(ChainPath chainPath) {
        return ((PermissionMapper)(this.mapper)).selectNotWithChainPath(chainPath);
    }

    @Override
    public List<Permission> queryWithResource(Resource resource) {
        return ((PermissionMapper)(this.mapper)).selectWithResource(resource);
    }

    @Override
    public List<Permission> queryNotWithResource(Resource resource) {
        return ((PermissionMapper)(this.mapper)).selectNotWithResource(resource);
    }

    @Override
    public Integer generateId(Permission record) {
        logger.info("systemId:{},ParentId:{}",record.getSystemId(),record.getParentId());
        Integer id = ((PermissionMapper)(this.mapper)).selectLastInsertId(record);
        Integer newId = -1;
        logger.info("id:{},newId:{}",id,newId);
        if(record.getParentId() != null){
            if(id == null){
                newId = record.getParentId() + 10;
            }else{
                newId = id + 10;
            }
        }else if (record.getSystemId() != null){
            if(id == null){
                newId = record.getSystemId() * 10000000 + 10000;
            }else{
                newId = id + 10000;
            }
        }

        logger.info("id:{},newId:{}",id,newId);

        return newId;
    }
}
