package com.fuzhutech.entity.auth;

import com.fuzhutech.common.entity.BaseEntity;

public class ResourcePermission implements BaseEntity {
    private Integer id;

    private Integer permissionId;

    private String resourceClass;

    private Integer resourceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getResourceClass() {
        return resourceClass;
    }

    public void setResourceClass(String resourceClass) {
        this.resourceClass = resourceClass == null ? null : resourceClass.trim();
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}