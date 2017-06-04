package com.fuzhutech.security.auth.shiro.cache;

import net.sf.ehcache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.List;

//用来拦截以update开头的方法，用来清除缓存
public class MethodCacheAfterAdvice implements AfterReturningAdvice,
        InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(MethodCacheInterceptor.class);

    private Cache cache;

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public MethodCacheAfterAdvice() {
        super();
    }

    public void afterReturning(Object arg0, Method arg1, Object[] arg2,
                               Object arg3) throws Throwable {
        String className = arg3.getClass().getName();
        List list = cache.getKeys();
        for (int i = 0; i < list.size(); i++) {
            String cacheKey = String.valueOf(list.get(i));
            if (cacheKey.startsWith(className)) {
                cache.remove(cacheKey);
                System.out.println("------清除缓存----");
                logger.debug("remove cache " + cacheKey);
            }
        }
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cache,
                "Need a cache. Please use setCache(Cache) create it.");
    }

}
