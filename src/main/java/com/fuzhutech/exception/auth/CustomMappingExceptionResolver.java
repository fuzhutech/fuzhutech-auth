package com.fuzhutech.exception.auth;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

public class CustomMappingExceptionResolver extends SimpleMappingExceptionResolver implements HandlerExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(CustomMappingExceptionResolver.class);
    DefaultHandlerExceptionResolver de;

    public CustomMappingExceptionResolver() {
    }

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.warn(ex.getMessage());
        return super.resolveException(request, response, handler, ex);
    }

    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return super.doResolveException(request, response, handler, ex);
    }
}
