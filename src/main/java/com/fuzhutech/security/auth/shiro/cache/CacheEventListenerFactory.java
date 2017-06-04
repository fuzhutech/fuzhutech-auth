package com.fuzhutech.security.auth.shiro.cache;

import java.util.Properties;

public class CacheEventListenerFactory extends net.sf.ehcache.event.CacheEventListenerFactory
{
    @Override
    public CacheEventListener createCacheEventListener(Properties properties)
    {
        // TODO Auto-generated method stub
        /*String beanName = properties.getProperty( "bean" );
        if ( beanName == null ) {
            throw new IllegalArgumentException( "缓存监听器名字未定义" );
        }*/
        //return (CacheEventListener) SpringContextHelper.getBean( beanName );


        return new CacheEventListener();
    }

}
