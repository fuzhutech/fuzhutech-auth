package com.fuzhutech.security.auth.shiro.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(RetryLimitCredentialsMatcher.class);
    private final static String DEFAULT_CHACHE_NAME = "passwordRetryCache";

    private final CacheManager cacheManager;
    private String retryLimitCacheName;
    //集群中可能会导致出现验证多过5次的现象，因为AtomicInteger只能保证单节点并发
    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitCredentialsMatcher(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
        this.retryLimitCacheName = DEFAULT_CHACHE_NAME;
    }

    public String getRetryLimitCacheName() {
        return retryLimitCacheName;
    }

    public void setRetryLimitCacheName(String retryLimitCacheName) {
        this.retryLimitCacheName = retryLimitCacheName;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        //UsernamePasswordToken var1 = (UsernamePasswordToken)token;

        logger.info("token,userName:{},password:{}", token.getPrincipal(), token.getCredentials());
        logger.info("info,userName:{},password:{}", info.getPrincipals(), info.getCredentials());
        //retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        logger.info("doCredentialsMatch retryCount:{}", retryCount);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > 5) {
            logger.warn("username: " + username + " tried to login more than 5 times in period");
            throw new ExcessiveAttemptsException("username: " + username + " tried to login more than 5 times in period"
            );
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //clear retry data
            passwordRetryCache.remove(username);
        }
        return matches;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.passwordRetryCache = cacheManager.getCache(retryLimitCacheName);
    }
}
