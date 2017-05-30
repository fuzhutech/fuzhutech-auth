package com.fuzhutech.security.auth.shiro.filter.mgt;

import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.web.filter.mgt.NamedFilterList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface FilterChainManager  extends  org.apache.shiro.web.filter.mgt.FilterChainManager {

    Map<String, Filter> getFilters();  //得到注册的拦截器

    NamedFilterList getChain(String var1); //获取拦截器链

    boolean hasChains();

    Set<String> getChainNames();  //得到所有拦截器的名字

    // /** = user,sysUser
    // /**就是拦截器链的名字，user,sysUser就是拦截器的名字
    FilterChain proxy(FilterChain var1, String var2);  //使用指定的拦截器链代理原始拦截器链

    void addFilter(String var1, Filter var2); //注册拦截器

    void addFilter(String var1, Filter var2, boolean var3); //注册拦截器

    void createChain(String var1, String var2);  //根据拦截器定义创建拦截器链

    void addToChain(String var1, String var2);

    void addToChain(String var1, String var2, String var3) throws ConfigurationException;  //添加拦截器（带有配置的）到指定的拦截器链

    FilterChain proxy1(FilterChain var1, List<String> chainNames);  //使用指定的拦截器链代理原始拦截器链

    public Map<String, NamedFilterList> getFilterChains();
}
