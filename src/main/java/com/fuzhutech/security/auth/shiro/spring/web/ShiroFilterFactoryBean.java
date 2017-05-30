package com.fuzhutech.security.auth.shiro.spring.web;

import com.fuzhutech.entity.auth.ChainDefinition;
import com.fuzhutech.service.auth.ChainDefinitionService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShiroFilterFactoryBean extends org.apache.shiro.spring.web.ShiroFilterFactoryBean {

    private static final transient Logger log = LoggerFactory.getLogger(ShiroFilterFactoryBean.class);

    private SpringShiroFilter springShiroFilter;

    @Autowired
    private ChainDefinitionService chainDefinitionService;


    public ShiroFilterFactoryBean() {
        log.info("ShiroFilterFactoryBean is creating");
    }

    @PostConstruct
    public void init() {
        log.info("ShiroFilterFactoryBean init");
        chainDefinitionService.setShiroFilterFactoryBean(this);
    }

    @Override
    public Map<String, String> getFilterChainDefinitionMap() {
        log.info("ShiroFilterFactoryBean getFilterChainDefinitionMap");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();

        List<ChainDefinition> list = chainDefinitionService.queryListByWhere(null);
        if (list == null || list.isEmpty())
            return filterChainDefinitionMap;

        for (ChainDefinition chainDefinition : list) {
            log.info("PathPattern:{},FilterChain:{}", chainDefinition.getPathPattern(), chainDefinition.getFilterChain());
            filterChainDefinitionMap.put(chainDefinition.getPathPattern(), chainDefinition.getFilterChain());
        }

        return filterChainDefinitionMap;
    }

    protected FilterChainManager createFilterChainManager() {

        FilterChainManager manager = super.createFilterChainManager();

        Map<String, String> chains = this.getFilterChainDefinitionMap();
        if(!CollectionUtils.isEmpty(chains)) {
            Iterator var12 = chains.entrySet().iterator();

            while(var12.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry)var12.next();
                String url = (String)entry.getKey();
                String chainDefinition = (String)entry.getValue();
                manager.createChain(url, chainDefinition);
            }
        }

        return manager;
    }

    protected AbstractShiroFilter createInstance() throws Exception {
        log.debug("Creating Shiro Filter instance.");
        SecurityManager securityManager = this.getSecurityManager();
        String msg;
        if (securityManager == null) {
            msg = "SecurityManager property must be set.";
            throw new BeanInitializationException(msg);
        } else if (!(securityManager instanceof WebSecurityManager)) {
            msg = "The security manager does not implement the WebSecurityManager interface.";
            throw new BeanInitializationException(msg);
        } else {
            org.apache.shiro.web.filter.mgt.FilterChainManager manager = this.createFilterChainManager();

            /*PathMatchingFilterChainResolver最重要的作用是：当请求url来的时候，他担任匹配工作（调用该类的getChain方法做匹配
            过滤执行doFilterInternal的时候，调用FilterChain resolved = resolver.getChain(request, response, origChain);*/
            PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
            chainResolver.setFilterChainManager(manager);

            springShiroFilter = new ShiroFilterFactoryBean.SpringShiroFilter((WebSecurityManager) securityManager, chainResolver);
            return springShiroFilter;
        }
    }

    //更换FilterChainManager,是否需要同步?
    public void ReplaceFilterChainManager() {
        log.info("ReplaceFilterChainManager");

        if (this.springShiroFilter == null) {
            return;
        }

        FilterChainManager manager = this.createFilterChainManager();
        PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
        chainResolver.setFilterChainManager(manager);

        springShiroFilter.setFilterChainResolver(chainResolver);
    }

    //extends OncePerRequestFilter
    private static final class SpringShiroFilter extends AbstractShiroFilter {

        protected SpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {

            if (webSecurityManager == null) {
                throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
            } else {
                this.setSecurityManager(webSecurityManager);
                if (resolver != null) {
                    this.setFilterChainResolver(resolver);
                }

            }
        }

    }
}
