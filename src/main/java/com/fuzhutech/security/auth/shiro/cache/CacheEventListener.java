package com.fuzhutech.security.auth.shiro.cache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheEventListener implements net.sf.ehcache.event.CacheEventListener
{

    private static final Logger logger = LoggerFactory.getLogger(CacheEventListener.class);

    @Override
    public void notifyElementRemoved(Ehcache ehcache, Element element) throws CacheException {
        logger.info("notifyElementRemoved:{}",element);
    }

    @Override
    public void notifyElementPut(Ehcache ehcache, Element element) throws CacheException {
        logger.info("notifyElementPut:{}",element);
    }

    @Override
    public void notifyElementUpdated(Ehcache ehcache, Element element) throws CacheException {
        logger.info("notifyElementUpdated:{}",element);
    }

    @Override
    public void notifyElementExpired(Ehcache ehcache, Element element) {
        logger.info("notifyElementExpired:{}",element);
    }

    @Override
    public void notifyElementEvicted(Ehcache ehcache, Element element) {
        logger.info("notifyElementEvicted:{}",element);
    }

    @Override
    public void notifyRemoveAll(Ehcache ehcache) {
        logger.info("notifyRemoveAll");
    }

    @Override
    public void dispose() {
        logger.info("dispose");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        //return null;
        logger.info("clone");
        return super.clone();
    }
}
