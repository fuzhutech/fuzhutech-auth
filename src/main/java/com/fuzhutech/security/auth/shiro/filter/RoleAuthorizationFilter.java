package com.fuzhutech.security.auth.shiro.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * roles1.自定义角色鉴权过滤器(满足其中一个角色则认证通过) 2.扩展异步请求认证提示功能;
 */
public class RoleAuthorizationFilter extends org.apache.shiro.web.filter.authz.RolesAuthorizationFilter {

    private final static Logger logger = LoggerFactory.getLogger(RoleAuthorizationFilter.class);

    /**
     * 原有逻辑“判断是否满足所有权限”修改为“只要满足其中一个角色”即可.
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return boolean
     * @throws IOException
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        logger.info("RoleAuthorizationFilter isAccessAllowed:{}",((HttpServletRequest)request).getRequestURL());

        Subject subject = this.getSubject(request, response);
        String[] rolesArray = (String[]) ((String[]) mappedValue);
        if (rolesArray != null && rolesArray.length != 0) {
            Set<String> roles = CollectionUtils.asSet(rolesArray);
            //return subject.hasAllRoles(roles);
            for (String role : roles) {
                if (subject.hasRole(role)) {
                    return true;
                }
            }
            return false;

        } else {
            return true;
        }
    }

    /**
     * 原有逻辑“跳转到loginUrl”
     *
     * @param request
     * @param response
     * @return boolean
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        logger.info("RoleAuthorizationFilter onAccessDenied:{}",((HttpServletRequest)request).getRequestURL());

        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() == null) {
            //this.saveRequestAndRedirectToLogin(request, response);
            if (isAjaxRequest(request)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("states", "-1");
                map.put("message", "您尚未登录或登录时间过长,请重新登录!");
                writeJson(map, response);
                HttpServletResponse res = WebUtils.toHttp(response);
                res.setHeader("oauthstatus", "401");
            } else {
                saveRequestAndRedirectToLogin(request, response);
            }

        } else {

            if (isAjaxRequest(request)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("states", "-1");
                map.put("message", "您没有足够的权限执行该操作!");
                writeJson(map, response);
            } else {
                //原有逻辑
                String unauthorizedUrl = this.getUnauthorizedUrl();
                if (StringUtils.hasText(unauthorizedUrl)) {
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    WebUtils.toHttp(response).sendError(401);
                }
            }

        }

        return false;
    }

    // 是否是Ajax请求
    private static boolean isAjaxRequest(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        /*String requestedWith = httpServletRequest.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }*/

        if(StringUtils.startsWithIgnoreCase(httpServletRequest.getRequestURI(),"/api/")) {
            return true;
        }else {
            return false;
        }
    }

    //输出JSON
    private void writeJson(Map<String, Object> map, ServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();

            ObjectMapper mapper = new ObjectMapper();
            out.write(mapper.writeValueAsString(map));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
