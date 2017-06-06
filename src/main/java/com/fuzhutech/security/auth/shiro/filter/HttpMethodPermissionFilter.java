package com.fuzhutech.security.auth.shiro.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

//rest
public class HttpMethodPermissionFilter extends org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter{

    private static final Logger logger = LoggerFactory.getLogger(HttpMethodPermissionFilter.class);

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        logger.info("isAccessAllowed mappedValue:{}",mappedValue);


        String[] perms = (String[])((String[])mappedValue);

        logger.info("length:{},perms:{}",perms.length,perms);

        String action = this.getHttpMethodAction(request);

        logger.info("action:{}",action);

        String[] resolvedPerms = this.buildPermissions(perms, action);

        logger.info("resolvedPerms:{}",resolvedPerms);

        return super.isAccessAllowed(request, response, mappedValue);
    }

}
