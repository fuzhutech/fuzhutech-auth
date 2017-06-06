package com.fuzhutech.service.auth;

import com.fuzhutech.common.service.BaseService;
import com.fuzhutech.entity.auth.Permission;

import java.util.List;

//部门管理
public interface PermissionService extends BaseService<Permission> {

    List<Permission> queryListByByUserId(Integer UserId);

}
