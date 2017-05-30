package com.fuzhutech.security.auth.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//authc
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(org.apache.shiro.web.filter.authc.FormAuthenticationFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = this.getSubject(request, response);
        return subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if(this.isLoginRequest(request, response)) {
            if(this.isLoginSubmission(request, response)) {
                if(log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }

                return this.executeLogin(request, response);
            } else {
                if(log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }

                return true;
            }
        } else {
            if(log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
            }

            log.info("FormAuthenticationFilter onAccessDenied:{}",((HttpServletRequest)request).getRequestURL());

            this.saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }

    //6.在解析的过程中，对每个url和对应的过滤条件，都会放到对应filter的appliedPaths中（在PathMatchingFilter中定义）
    //protected Map<String, Object> appliedPaths = new LinkedHashMap();
    @Override
    public Filter processPathConfig(String path, String config) {
        String[] values = null;
        if(config != null) {
            values = StringUtils.split(config);
        }

        this.appliedPaths.put(path, values);
        return this;
    }
}
