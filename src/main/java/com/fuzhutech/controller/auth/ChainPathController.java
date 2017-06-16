package com.fuzhutech.controller.auth;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.auth.ChainPath;
import com.fuzhutech.entity.auth.ChainPathPermission;
import com.fuzhutech.service.auth.ChainDefinitionService;
import com.fuzhutech.service.auth.ChainPathPermissionService;
import com.fuzhutech.service.auth.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 用户
@RestController
@RequestMapping("/chain-paths")
public class ChainPathController extends AuthRestfulController<ChainPath> {

    private static Logger logger = LoggerFactory.getLogger(ChainPathController.class);

    @Autowired
    PermissionService permissionService;

    @Autowired
    ChainPathPermissionService chainPathPermissionService;

    @RequestMapping(value = "/{id}/permissions", method = RequestMethod.GET)
    public ResponseResult getUserList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int chainPathId) {
        ResponseResult responseResult = new ResponseResult();
        ChainPath chainPath = new ChainPath();
        chainPath.setId(chainPathId);
        try {
            //responseResult.putData("sourceList", this.permissionService.queryNotWithChainPath(chainPath));
            responseResult.putData("sourceList", this.permissionService.queryListByWhere(null));
            responseResult.putData("targetList", this.permissionService.queryWithChainPath(chainPath));
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
    public ResponseResult editUserList(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int chainPathId,
                                       @RequestBody String permIds) {
        try {
            //注意，需要额外处理字符串中的双引号
            this.chainPathPermissionService.insertPermissionWithChainPath(permIds.replace("\"", ""), chainPathId);
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            logger.error("编辑失败：{}", ex);
            return new ResponseResult(ResponseResult.FAILURE, ex.getMessage());
        }
    }

}
