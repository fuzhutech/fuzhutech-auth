package com.fuzhutech.security.auth.shiro.cache;

import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 监听器可以插件化。Ehcache 1.2提供了CacheManagerEventListener和CacheEventListener接口，实现可以插件化，并且可以在ehcache.xml里配置
 * Created by zhangfeng on 2017/6/4.
 */

//Ehcache中定义了一个CacheManagerEventListener接口来监听CacheManager的事件
    //http://blog.csdn.net/ethan_fu/article/details/46787123
public class CacheManagerEventListenerFactory extends net.sf.ehcache.event.CacheManagerEventListenerFactory {

    private static final Logger logger = LoggerFactory.getLogger(CacheManagerEventListenerFactory.class);

    @Override
    public net.sf.ehcache.event.CacheManagerEventListener createCacheManagerEventListener(CacheManager cacheManager, Properties properties) {
        logger.info("CacheManagerEventListenerFactory creating");
        return new CacheManagerEventListener(cacheManager);
    }
}
