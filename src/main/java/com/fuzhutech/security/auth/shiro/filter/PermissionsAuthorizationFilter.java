package com.fuzhutech.security.auth.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

//perms
public class PermissionsAuthorizationFilter extends org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter {

    private static final Logger logger = LoggerFactory.getLogger(PermissionsAuthorizationFilter.class);

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        logger.info("mappedValue:{}",mappedValue);

        Subject subject = this.getSubject(request, response);
        String[] perms = (String[])((String[])mappedValue);
        logger.info("length:{}",perms.length);
        boolean isPermitted = true;
        if(perms != null && perms.length > 0) {
            if(perms.length == 1) {
                if(!subject.isPermitted(perms[0])) {
                    isPermitted = false;
                }
            } else if(!subject.isPermittedAll(perms)) {
                isPermitted = false;
            }
        }

        return isPermitted;
    }
}
