package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.auth.Permission;
import com.fuzhutech.service.auth.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

// 权限管理
@RestController
@RequestMapping("/permissions")
public class PermissionController extends AuthRestfulController<Permission> {

    private static Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Override
    protected ResponseResult deleteInternal(HttpServletRequest request, HttpServletResponse response, Integer id) {
        try {
            ((PermissionService)this.service).deleteByIdAndParent(id);
            return new ResponseResult(1);
        } catch (RuntimeException var5) {
            logger.error("删除失败：{}", var5);
            return new ResponseResult(-1, var5.getMessage());
        }
    }

    @RequestMapping(value = "/id", method = {RequestMethod.PUT})
    public ResponseResult generateId(HttpServletRequest request, HttpServletResponse response, @RequestBody Permission model) {
        logger.info("systemid:{},parentId:{}",model.getSystemId(),model.getParentId());
        ResponseResult responseResult = new ResponseResult();
        try {
        responseResult.putData("id",((PermissionService) this.service).generateId(model));
        responseResult.setStatus(ResponseResult.SUCCESS);
        return responseResult;
        } catch (RuntimeException ex) {
            logger.error("生成ID失败：{}", ex);
            responseResult.setStatus(ResponseResult.FAILURE);
            responseResult.setMessage(ex.getMessage());
            return responseResult;
        }
    }

}
