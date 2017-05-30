package com.fuzhutech.security.auth.shiro.filter.mgt;

import org.apache.shiro.web.filter.mgt.*;

public interface FilterChainResolver extends org.apache.shiro.web.filter.mgt.FilterChainResolver{

    public void setFilterChainManager(org.apache.shiro.web.filter.mgt.FilterChainManager filterChainManager);

}
