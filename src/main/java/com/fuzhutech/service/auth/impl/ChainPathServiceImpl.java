package com.fuzhutech.service.auth.impl;


import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.entity.auth.ChainPath;
import com.fuzhutech.service.auth.ChainPathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ChainPathServiceImpl extends BaseServiceImpl<ChainPath> implements ChainPathService {

    private static Logger logger = LoggerFactory.getLogger(ChainPathServiceImpl.class);

}
