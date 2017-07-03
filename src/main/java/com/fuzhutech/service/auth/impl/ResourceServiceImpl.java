package com.fuzhutech.service.auth.impl;

import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.auth.ResourceMapper;
import com.fuzhutech.entity.auth.Resource;
import com.fuzhutech.entity.auth.Role;
import com.fuzhutech.entity.auth.User;
import com.fuzhutech.service.auth.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {

    @Override
    public List<Resource> queryWithRole(Role role) {
        return ((ResourceMapper) mapper).selectWithRole(role);
    }

    @Override
    public List<Resource> queryNotWithRole(Role role) {
        return ((ResourceMapper) mapper).selectNotWithRole(role);
    }

    @Override
    public List<Resource> queryWithUser(User user){
        return ((ResourceMapper) mapper).selectWithUser(user);
    }

    @Override
    public List<Resource> queryWithSystem(int sysId) {
        return ((ResourceMapper) mapper).selectWithSystem(sysId);
    }

    @Override
    public Integer generateId(Resource record) {
        Integer id = ((ResourceMapper)(this.mapper)).selectLastInsertId(record);
        Integer newId = -1;
        if(record.getResourceType() == 0){
            //添加菜单，根据SystemId直接生成
            if (record.getSystemId() != null){
                if(id == null){
                    newId = record.getSystemId() * 10000000 + 10000;
                }else{
                    newId = id + 10000;
                }
            }
        }else{
            //添加权限明细
            if(record.getParentId() != null){
                if(id == null){
                    newId = record.getParentId() + 10;
                }else{
                    newId = id + 10;
                }
            }
        }

        return newId;
    }

    @Override
    public Integer deleteByIdAndParent(Integer id) {
        int i = this.mapper.deleteByPrimaryKey(id);
        Resource record = new Resource();
        record.setParentId(id);
        record.setResourceType(1);
        return i + this.mapper.deleteByWhere(record);
    }
}
