package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.auth.ChainDefinition;
import com.fuzhutech.service.auth.ChainDefinitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 用户
@RestController
@RequestMapping("/chains")
public class ChainDefinitionController extends AuthRestfulController<ChainDefinition> {

    private static Logger logger = LoggerFactory.getLogger(ChainDefinitionController.class);

    @RequestMapping(value = "refresh", method = {RequestMethod.PUT})
    public ResponseResult edit(HttpServletRequest request, HttpServletResponse response) {
        logger.info("ChainDefinitionController url:{}",request.getRequestURL());
        try {
            ((ChainDefinitionService) this.service).replaceFilterChain();
            return new ResponseResult(1);
        } catch (RuntimeException var5) {
            return new ResponseResult(-1, (Object) null, var5.getMessage());
        }

    }
}
