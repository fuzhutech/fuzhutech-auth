package com.fuzhutech.service.auth.impl;


import com.fuzhutech.common.service.impl.BaseServiceImpl;
import com.fuzhutech.entity.auth.ChainDefinition;
import com.fuzhutech.security.auth.shiro.spring.web.ShiroFilterFactoryBean;
import com.fuzhutech.service.auth.ChainDefinitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ChainDefinitionServiceImpl extends BaseServiceImpl<ChainDefinition> implements ChainDefinitionService {

    private static Logger logger = LoggerFactory.getLogger(ChainDefinitionServiceImpl.class);

    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Override
    public void setShiroFilterFactoryBean(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        logger.info("setShiroFilterFactoryBean");
        this.shiroFilterFactoryBean = shiroFilterFactoryBean;
    }

    @Override
    public void replaceFilterChain() {
        logger.info("replaceFilterChain");
        this.shiroFilterFactoryBean.ReplaceFilterChainManager();
    }


}
