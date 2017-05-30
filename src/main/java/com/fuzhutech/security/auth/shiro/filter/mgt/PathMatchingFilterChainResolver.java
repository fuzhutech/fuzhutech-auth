package com.fuzhutech.security.auth.shiro.filter.mgt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhangfeng on 2017/5/27.
 */
public class PathMatchingFilterChainResolver extends org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver implements FilterChainResolver{

    private static final transient Logger log = LoggerFactory.getLogger(PathMatchingFilterChainResolver.class);

    private FilterChainManager filterChainManager;

    public FilterChainManager getFilterChainManager() {
        return this.filterChainManager;
    }

    public void setCustomFilterChainManager(FilterChainManager filterChainManager) {
        this.filterChainManager = filterChainManager;
        setFilterChainManager(filterChainManager);
    }

    //过滤执行doFilterInternal的时候，调用
    //FilterChain resolved = resolver.getChain(request, response, origChain);

    @Override
    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = this.getFilterChainManager();
        if(!filterChainManager.hasChains()) {
            return null;
        } else {
            String requestURI = this.getPathWithinApplication(request);
            Iterator var6 = filterChainManager.getChainNames().iterator();

            String pathPattern;
            do {
                if(!var6.hasNext()) {
                    return null;
                }

                pathPattern = (String)var6.next();
            } while(!this.pathMatches(pathPattern, requestURI));

            if(log.isTraceEnabled()) {
                log.trace("Matched path pattern [" + pathPattern + "] for requestURI [" + requestURI + "].  " + "Utilizing corresponding filter chain...");
            }

            return filterChainManager.proxy(originalChain, pathPattern);
        }
    }

    //@Override   网络改造
    public FilterChain getChain1(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = this.getFilterChainManager();
        if(!filterChainManager.hasChains()) {
            return null;
        } else {
            String requestURI = this.getPathWithinApplication(request);

            List<String> chainNames = new ArrayList<String>();
            for(String pathPattern : filterChainManager.getChainNames()){
                if(this.pathMatches(pathPattern,requestURI)){
                    chainNames.add(pathPattern);
                }
            }

            if(chainNames.isEmpty()){
                return  null;
            }

            return filterChainManager.proxy1(originalChain,chainNames);

        }
    }
}
