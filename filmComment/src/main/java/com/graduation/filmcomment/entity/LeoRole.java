package com.graduation.filmcomment.entity;

import java.io.Serializable;
import java.util.List;

/**
 * (LeoRole)实体类
 *
 * @author makejava
 * @since 2020-11-01 21:23:31
 */
public class LeoRole implements Serializable {
    private static final long serialVersionUID = 710997740319790708L;

    private Integer leoRoleId;

    private String leoRoleName;

    private List<LeoPermission> leoPermissions;


    public Integer getLeoRoleId() {
        return leoRoleId;
    }

    public void setLeoRoleId(Integer leoRoleId) {
        this.leoRoleId = leoRoleId;
    }

    public String getLeoRoleName() {
        return leoRoleName;
    }

    public void setLeoRoleName(String leoRoleName) {
        this.leoRoleName = leoRoleName;
    }

    public List<LeoPermission> getLeoPermissions() {
        return leoPermissions;
    }

    public void setLeoPermissions(List<LeoPermission> leoPermissions) {
        this.leoPermissions = leoPermissions;
    }

    @Override
    public String toString() {
        return "LeoRole{" +
                "leoRoleId=" + leoRoleId +
                ", leoRoleName='" + leoRoleName + '\'' +
                ", leoPermissions=" + leoPermissions +
                '}';
    }
}