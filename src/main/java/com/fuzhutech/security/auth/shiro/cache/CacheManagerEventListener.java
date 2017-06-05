package com.fuzhutech.security.auth.shiro.cache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangfeng on 2017/6/4.
 */
public class CacheManagerEventListener implements net.sf.ehcache.event.CacheManagerEventListener {

    private static final Logger logger = LoggerFactory.getLogger(CacheManagerEventListener.class);

    private final CacheManager cacheManager;

    public CacheManagerEventListener(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void init() throws CacheException {
        logger.info("init....");
    }

    @Override
    public Status getStatus() {
        logger.info("getStatus....");
        return null;
    }

    @Override
    public void dispose() throws CacheException {
        logger.info("dispose....");
    }

    @Override
    public void notifyCacheAdded(String cacheName) {
        logger.info("cacheAdded......." + cacheName);
        logger.info("cacheManager.getCache:{}",cacheManager.getCache(cacheName));
    }

    @Override
    public void notifyCacheRemoved(String cacheName) {
        logger.info("cacheRemoved......" + cacheName);
    }
}
