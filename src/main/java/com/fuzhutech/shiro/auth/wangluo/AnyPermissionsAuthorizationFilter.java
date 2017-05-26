package com.fuzhutech.shiro.auth.wangluo;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by Administrator on 2017/5/10.
 */
public class AnyPermissionsAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] perms = (String[]) mappedValue;

        for (String perm : perms) {
            if (subject.isPermitted(perm)) {
                return true;
            }
        }

        return false;
    }
}
