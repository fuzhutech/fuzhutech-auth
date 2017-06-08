package com.fuzhutech.security.auth.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//rest
public class HttpMethodPermissionFilter extends org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter{

    private static final Logger logger = LoggerFactory.getLogger(HttpMethodPermissionFilter.class);

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        logger.info("HttpMethodPermissionFilter isAccessAllowed:{}",((HttpServletRequest)request).getRequestURL());

        String[] perms = (String[])((String[])mappedValue);
        //logger.info("isAccessAllowed mappedValue:{},perms:{}",mappedValue,perms);

        String action = this.getHttpMethodAction(request);
        //logger.info("action:{}",action);

        String[] resolvedPerms = this.buildPermissions(perms, action);
        logger.info("mappedValue:{},resolvedPerms:{}",mappedValue,resolvedPerms);

        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        logger.info("HttpMethodPermissionFilter onAccessDenied:{}",((HttpServletRequest)request).getRequestURL());

        Subject subject = this.getSubject(request, response);
        if(subject.getPrincipal() == null) {
            this.saveRequestAndRedirectToLogin(request, response);
        } else {
            String unauthorizedUrl = this.getUnauthorizedUrl();
            if(StringUtils.hasText(unauthorizedUrl)) {
                WebUtils.issueRedirect(request, response, unauthorizedUrl);
            } else {
                WebUtils.toHttp(response).sendError(401);
            }
        }

        return false;
    }

}
