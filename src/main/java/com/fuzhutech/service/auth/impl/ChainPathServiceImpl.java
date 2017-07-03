package com.fuzhutech.service.auth.impl;


import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.dao.auth.ChainPathMapper;
import com.fuzhutech.entity.auth.ChainPath;
import com.fuzhutech.service.auth.ChainPathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ChainPathServiceImpl extends BaseServiceImpl<ChainPath> implements ChainPathService {

    private static Logger logger = LoggerFactory.getLogger(ChainPathServiceImpl.class);

    @Override
    public Integer generateId(ChainPath record) {
        Integer id = ((ChainPathMapper)(this.mapper)).selectLastInsertId(record);
        Integer newId = -1;
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

        return newId;
    }

    @Override
    public Integer deleteByIdAndParent(Integer id) {
        int i = this.mapper.deleteByPrimaryKey(id);
        ChainPath record = new ChainPath();
        record.setParentId(id);
        return i + this.mapper.deleteByWhere(record);
    }

}
