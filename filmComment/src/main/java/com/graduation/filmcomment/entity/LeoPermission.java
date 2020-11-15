package com.graduation.filmcomment.entity;

import java.io.Serializable;

/**
 * (LeoPermission)实体类
 *
 * @author makejava
 * @since 2020-11-01 21:23:52
 */
public class LeoPermission implements Serializable {
    private static final long serialVersionUID = -45245193081893250L;

    private Integer leoPermissionId;

    private String leoPermissionName;

    private String leoPermissionUrl;


    public Integer getLeoPermissionId() {
        return leoPermissionId;
    }

    public void setLeoPermissionId(Integer leoPermissionId) {
        this.leoPermissionId = leoPermissionId;
    }

    public String getLeoPermissionName() {
        return leoPermissionName;
    }

    public void setLeoPermissionName(String leoPermissionName) {
        this.leoPermissionName = leoPermissionName;
    }

    public String getLeoPermissionUrl() {
        return leoPermissionUrl;
    }

    public void setLeoPermissionUrl(String leoPermissionUrl) {
        this.leoPermissionUrl = leoPermissionUrl;
    }

    @Override
    public String toString() {
        return "LeoPermission{" +
                "leoPermissionId=" + leoPermissionId +
                ", leoPermissionName='" + leoPermissionName + '\'' +
                ", leoPermissionUrl='" + leoPermissionUrl + '\'' +
                '}';
    }
}