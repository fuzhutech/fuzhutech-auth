package com.fuzhutech.security.auth.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

//rest
public class HttpMethodPermissionFilter extends org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter{

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
