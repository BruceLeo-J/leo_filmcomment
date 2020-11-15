package com.graduation.filmcomment.entity;

import java.io.Serializable;

/**
 * (LeoCity)实体类
 *
 * @author makejava
 * @since 2020-10-21 22:34:06
 */
public class LeoCity implements Serializable {
    private static final long serialVersionUID = -57537812336923885L;

    private Integer leoCityId;

    private String leoCityName;

    private Integer leoCityProvinceId;


    public Integer getLeoCityId() {
        return leoCityId;
    }

    public void setLeoCityId(Integer leoCityId) {
        this.leoCityId = leoCityId;
    }

    public String getLeoCityName() {
        return leoCityName;
    }

    public void setLeoCityName(String leoCityName) {
        this.leoCityName = leoCityName;
    }

    public Integer getLeoCityProvinceId() {
        return leoCityProvinceId;
    }

    public void setLeoCityProvinceId(Integer leoCityProvinceId) {
        this.leoCityProvinceId = leoCityProvinceId;
    }

}