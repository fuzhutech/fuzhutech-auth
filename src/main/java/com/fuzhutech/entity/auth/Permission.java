package com.fuzhutech.entity.auth;

import com.fuzhutech.common.entity.BaseEntity;

public class Permission implements BaseEntity {
    private Integer id;

    private Integer groupId;

    private Integer systemId;

    private String code;

    private String name;

    private Integer type;

    private Integer builtIn;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBuiltIn() {
        return builtIn;
    }

    public void setBuiltIn(Integer builtIn) {
        this.builtIn = builtIn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}