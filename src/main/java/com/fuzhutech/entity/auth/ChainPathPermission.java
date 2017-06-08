package com.fuzhutech.entity.auth;

import com.fuzhutech.common.entity.BaseEntity;
import java.util.Date;

public class ChainPathPermission implements BaseEntity {
    private Integer id;

    private Integer chainPathId;

    private Integer permissionId;

    private Date createTime;

    private Date lastModifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChainPathId() {
        return chainPathId;
    }

    public void setChainPathId(Integer chainPathId) {
        this.chainPathId = chainPathId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}