package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.auth.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


//权限管理
@RestController
@RequestMapping("/roles")
public class RoleController extends AuthRestfulController<Role> {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    /*@Override
    protected List<Role> getListInternal(HttpServletRequest request, HttpServletResponse response, Role model) {
        throw  new UnauthenticatedException("ceshi");
        //return this.service.queryListByWhere(model);
    }*/

    /**
     * 登录认证异常
     */
    /*@ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public ResponseResult authenticationException(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseResult(ResponseResult.FAILURE, null,"未登录");
    }*/

    /**
     * 权限异常
     */
    /*@ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public ResponseResult authorizationException(HttpServletRequest request, HttpServletResponse response) {

        return new ResponseResult(ResponseResult.FAILURE, null,"无权限");
    }*/

}
