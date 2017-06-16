package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.auth.ChainPath;
import com.fuzhutech.entity.auth.Resource;
import com.fuzhutech.entity.auth.ResourcePermission;
import com.fuzhutech.service.auth.PermissionService;
import com.fuzhutech.service.auth.ResourcePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 部门管理
@RestController
@RequestMapping("/resources")
public class ResourceController extends AuthRestfulController<Resource> {

    private static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    PermissionService permissionService;

    @Autowired
    ResourcePermissionService resourcePermissionService;

    @RequestMapping(value = "/{id}/permissions", method = RequestMethod.GET)
    public ResponseResult getUserList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int resourceId) {
        ResponseResult responseResult = new ResponseResult();
        Resource resource = new Resource();
        resource.setId(resourceId);
        try {
            //responseResult.putData("sourceList", this.permissionService.queryNotWithResource(resource));
            responseResult.putData("sourceList", this.permissionService.queryListByWhere(null));
            responseResult.putData("targetList", this.permissionService.queryWithResource(resource));
            responseResult.setStatus(ResponseResult.SUCCESS);
            return responseResult;
        } catch (RuntimeException ex) {
            logger.error("getUserList失败：{}", ex);
            responseResult.setStatus(ResponseResult.FAILURE);
            responseResult.setMessage(ex.getMessage());
            return responseResult;
        }
    }

    @RequestMapping(value = "/{id}/permissions", method = RequestMethod.PUT)
    public ResponseResult editUserList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int resourceId,
                                       @RequestBody String permIds) {
        try {
            //注意，需要额外处理字符串中的双引号
            this.resourcePermissionService.insertPermissionWithResource(permIds.replace("\"", ""), resourceId);
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            logger.error("编辑失败：{}", ex);
            return new ResponseResult(ResponseResult.FAILURE, ex.getMessage());
        }
    }

}
