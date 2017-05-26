package com.fuzhutech.shiro.auth.wangluo;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Section>{

    private static Logger logger = LoggerFactory.getLogger(ChainDefinitionSectionMetaSource.class);

    public static final String PREMISSION_STRING="perms[\"{0}\"]";

    private String filterChainDefinitions;

    public String getFilterChainDefinitions() {
        logger.info("ChainDefinitionSectionMetaSource getFilterChainDefinitions:{}"+ filterChainDefinitions);
        return filterChainDefinitions;
    }

    //@Resource
    public void setFilterChainDefinitions(String filterChainDefinitions) {
        logger.info("ChainDefinitionSectionMetaSource setFilterChainDefinitions");
        String fiter="";//改正后的url配置
        /*List<Privilege> list = privilegeDao.getAll();
        for (Iterator<Privilege> it = list.iterator(); it.hasNext();) {
            Privilege privilege = it.next();
            if(!StringUtils.isEmpty(privilege.getUrl())) {
                fiter+="/"+privilege.getUrl()+" = authc," +MessageFormat.format(PREMISSION_STRING,privilege.getPerms()) +"\n";
            }//追加beans.xml中已经有的过滤
        }*/
        System.out.println(filterChainDefinitions+fiter);
        this.filterChainDefinitions = filterChainDefinitions+fiter;
    }

    public Section getObject(){

        logger.info("ChainDefinitionSectionMetaSource getObject");

        Ini ini = new Ini();//网上好多都是在这里配置URL的。但是发现是错误的。
        ini.load(filterChainDefinitions);
        Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        return section;
    }

    public Class<?> getObjectType() {
        logger.info("ChainDefinitionSectionMetaSource getObjectType");

        return this.getClass();
    }

    public boolean isSingleton() {
        return false;
    }

}
