package com.fuzhutech.service.auth;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.ChainDefinition;
import com.fuzhutech.security.auth.shiro.spring.web.ShiroFilterFactoryBean;

public interface ChainDefinitionService extends BaseService<ChainDefinition> {

    void setShiroFilterFactoryBean(ShiroFilterFactoryBean shiroFilterFactoryBean);

    void replaceFilterChain();

}
