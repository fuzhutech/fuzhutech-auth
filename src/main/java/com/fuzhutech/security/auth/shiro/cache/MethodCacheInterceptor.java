package com.fuzhutech.security.auth.shiro.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.Serializable;

//用来拦截以get和find开头的方法（用于缓存结果）
public class MethodCacheInterceptor implements MethodInterceptor,
        InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(MethodCacheInterceptor.class);

    private Cache cache;

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(cache,
                "A cache is required. Use setCache(Cache) to provide one.");
    }
    /**
     * 拦截Service/DAO 的方法，并查找该结果是否存在，如果存在就返回cache 中的值， 31 *
     * 否则，返回数据库查询结果，并将查询结果放入cache 32
     */
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String targetName = methodInvocation.getThis().getClass().getName();
        String methodName = methodInvocation.getMethod().getName();
        Object[] arguments = methodInvocation.getArguments();
        Object result;

        logger.info("Find object from cache is " + cache.getName());

        String cacheKey = getCacheKey(targetName, methodName, arguments);
        Element element = null;
        synchronized (this) {
            element = cache.get(cacheKey);
            if (element == null) {
                //调用实际的方法
                logger.info("Hold up method , Get method result and create cache........!");
                result = methodInvocation.proceed();
                //element = new Element(cacheKey, (Serializable) result);
                element = new Element(cacheKey, result);
                System.out.println("-----非缓存中查找，查找后放入缓存");
                cache.put(element);
            }
        }
        return element.getObjectValue();
    }

    /**
     * 获得cache key 的方法，cache key 是Cache 中一个Element 的唯一标识 55 * cache key
     * 包括包名+类名+方法名，如 com.co.cache.service.UserServiceImpl.getAllUser 56
     */
    private String getCacheKey(String targetName, String methodName,
                               Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        sb.append(targetName).append(".").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sb.append(".").append(arguments[i]);
            }
        }
        return sb.toString();
    }
}
