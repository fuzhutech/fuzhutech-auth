package com.fuzhutech.shiro.auth.wangluo;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangfeng on 2017/5/26.
 */
public class RolesAnyAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        String url = servletRequest.getParameter("url");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String reqUrl = httpRequest.getRequestURL().toString();
        System.out.println("请求地址：" + reqUrl);
        System.out.println("参数地址：" + url);

        String re = "\\/(.*?)\\/";
        Pattern p = Pattern.compile(re);
        Matcher m = p.matcher(url);
        String server = "";
        int n = 0;
        while (m.find()) {
            server = m.group(1);
            if (n == 1) {
                break;
            }
            n++;
        }

        Subject subject = getSubject(servletRequest, servletResponse);
        System.out.println("访问服务名：" + server + "；用户角色是否存在：" + subject.hasRole(server));
        if (subject.hasRole(server)) {
            //进一步看是否是访问分类
            String rsearch_cid = servletRequest.getParameter("search_cid");
            if (rsearch_cid != null) {
                try {
                    subject.checkPermission(server + "&" + rsearch_cid);
                } catch (Exception e) {
                    //抛出异常表示无权限
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
