package com.fuzhutech.security.auth.shiro.filter.mgt;

import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.PathConfigProcessor;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.apache.shiro.web.filter.mgt.SimpleNamedFilterList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangfeng on 2017/5/27.
 */
public class DefaultFilterChainManager extends org.apache.shiro.web.filter.mgt.DefaultFilterChainManager implements FilterChainManager{

    private static final transient Logger log = LoggerFactory.getLogger(DefaultFilterChainManager.class);

    public DefaultFilterChainManager() {
        super();
        log.info("DefaultFilterChainManager is created");
    }

    public DefaultFilterChainManager(FilterConfig filterConfig) {
        super(filterConfig);
        log.info("DefaultFilterChainManager is created1");
    }

    //private Map<String, Filter> filters = new LinkedHashMap();

    @Override
    public void createChain(String chainName, String chainDefinition) {
        if(!StringUtils.hasText(chainName)) {
            throw new NullPointerException("chainName cannot be null or empty.");
        } else if(!StringUtils.hasText(chainDefinition)) {
            throw new NullPointerException("chainDefinition cannot be null or empty.");
        } else {
            if(log.isDebugEnabled()) {
                log.debug("Creating chain [" + chainName + "] from String definition [" + chainDefinition + "]");
            }

            //将权限分割  "authc, roles[admin,user], perms[file:edit]",分割为{ "authc", "roles[admin,user]", "perms[file:edit]" }
            String[] filterTokens = this.splitChainDefinition(chainDefinition);
            String[] var4 = filterTokens;
            int var5 = filterTokens.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String token = var4[var6];
                String[] nameConfigPair = this.toNameConfigPair(token);
                this.addToChain(chainName, nameConfigPair[0], nameConfigPair[1]);
            }

        }
    }

    @Override
    public void addToChain(String chainName, String filterName, String chainSpecificFilterConfig) {
        if(!StringUtils.hasText(chainName)) {
            throw new IllegalArgumentException("chainName cannot be null or empty.");
        } else {
            //todo:
            Filter filter = this.getFilter(filterName);
            if(filter == null) {
                throw new IllegalArgumentException("There is no filter with name '" + filterName + "' to apply to chain [" + chainName + "] in the pool of available Filters.  Ensure a " + "filter with that name/path has first been registered with the addFilter method(s).");
            } else {

                this.applyChainConfig(chainName, filter, chainSpecificFilterConfig);

                NamedFilterList chain = this.ensureChain(chainName);
                chain.add(filter);
            }
        }
    }

    //private Map<String, NamedFilterList> filterChains = new LinkedHashMap();
    @Override
    protected NamedFilterList ensureChain(String chainName) {
        NamedFilterList chain = this.getChain(chainName);
        if(chain == null) {
            chain = new SimpleNamedFilterList(chainName);

            this.getFilterChains().put(chainName, chain);
            //this.filterChains.put(chainName, chain);
        }

        return (NamedFilterList)chain;
    }

    @Override
    protected void applyChainConfig(String chainName, Filter filter, String chainSpecificFilterConfig) {
        if(log.isDebugEnabled()) {
            log.debug("Attempting to apply path [" + chainName + "] to filter [" + filter + "] " + "with config [" + chainSpecificFilterConfig + "]");
        }

        if(filter instanceof PathConfigProcessor) {
            ((PathConfigProcessor)filter).processPathConfig(chainName, chainSpecificFilterConfig);
        } else if(StringUtils.hasText(chainSpecificFilterConfig)) {
            String msg = "chainSpecificFilterConfig was specified, but the underlying Filter instance is not an 'instanceof' " + PathConfigProcessor.class.getName() + ".  This is required if the filter is to accept " + "chain-specific configuration.";
            throw new ConfigurationException(msg);
        }

    }

    @Override
    public FilterChain proxy(FilterChain original, String chainName) {
        NamedFilterList configured = this.getChain(chainName);
        if(configured == null) {
            String msg = "There is no configured chain under the name/key [" + chainName + "].";
            throw new IllegalArgumentException(msg);
        } else {
            return configured.proxy(original);
        }
    }

    //private Map<String, NamedFilterList> filterChains = new LinkedHashMap();
    @Override
    public NamedFilterList getChain(String chainName) {
        //return (NamedFilterList)this.filterChains.get(chainName);
        return super.getChain(chainName);
    }


    //@Override  网络改造1
    public FilterChain proxy1(FilterChain original, List<String> chainNames) {
        NamedFilterList configured = new SimpleNamedFilterList(chainNames.toString());
        for(String chainName : chainNames){
            configured.addAll(getChain(chainName));
        }

        return configured.proxy(original);
    }

    //网络改造
    @PostConstruct
    public void init(){
        Map<String,Filter> filters = getFilters();
    }
}
