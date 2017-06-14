package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.common.entity.BaseEntity;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class AuthRestfulController<T extends BaseEntity> extends com.fuzhutech.common.controller.RestfulController<T>{
    //

    /**
     * 登录认证异常
     */
    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public ResponseResult authenticationException(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseResult(ResponseResult.FAILURE,"未登录");
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public ResponseResult authorizationException(HttpServletRequest request, HttpServletResponse response) {

        return new ResponseResult(ResponseResult.FAILURE, "无权限");
    }
}
