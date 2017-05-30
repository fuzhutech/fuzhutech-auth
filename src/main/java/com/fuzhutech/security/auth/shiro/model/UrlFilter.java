package com.fuzhutech.security.auth.shiro.model;

import java.io.Serializable;

//表示拦截的URL和角色/权限之间的关系，多个角色/权限之间通过逗号分隔
public class UrlFilter implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    private int id;
    private String name; //url名称/描述
    private String url;  //地址
    private String role; //所需要的角色，可省略
    private String permissions; //所需要的权限，可省略
}
