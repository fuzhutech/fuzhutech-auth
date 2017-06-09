package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.auth.ChainPath;
import com.fuzhutech.service.auth.ChainDefinitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 用户
@RestController
@RequestMapping("/chain-paths")
public class ChainPathController extends AuthRestfulController<ChainPath> {

    private static Logger logger = LoggerFactory.getLogger(ChainPathController.class);

}
