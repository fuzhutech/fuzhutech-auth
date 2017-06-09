package com.fuzhutech.controller.auth;

import com.fuzhutech.entity.auth.ChainPathPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 用户
@RestController
@RequestMapping("/chain-path-permissions")
public class ChainPathPermissionController extends AuthRestfulController<ChainPathPermission> {

    private static Logger logger = LoggerFactory.getLogger(ChainPathPermissionController.class);
    
}
