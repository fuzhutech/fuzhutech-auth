package com.fuzhutech.entity.auth;

import com.fuzhutech.common.entity.BaseEntity;

public class ChainDefinition implements BaseEntity {
    private Integer id;

    private String pathPattern;

    private String filterChain;

    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPathPattern() {
        return pathPattern;
    }

    public void setPathPattern(String pathPattern) {
        this.pathPattern = pathPattern == null ? null : pathPattern.trim();
    }

    public String getFilterChain() {
        return filterChain;
    }

    public void setFilterChain(String filterChain) {
        this.filterChain = filterChain == null ? null : filterChain.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}