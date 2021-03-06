package com.fuzhutech.dao.auth;

import com.fuzhutech.common.dao.BaseMapper;
import com.fuzhutech.entity.auth.ChainPath;

public interface ChainPathMapper extends BaseMapper<ChainPath> {

    Integer selectLastInsertId(ChainPath chainPath);

}