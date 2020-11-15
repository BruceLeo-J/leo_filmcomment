package com.graduation.filmcomment.entity;

import java.io.Serializable;

/**
 * (LeoProvince)实体类
 *
 * @author makejava
 * @since 2020-10-21 22:34:19
 */
public class LeoProvince implements Serializable {
    private static final long serialVersionUID = -79073411128043254L;

    private Integer leoProvinceId;

    private String leoProvinceName;


    public Integer getLeoProvinceId() {
        return leoProvinceId;
    }

    public void setLeoProvinceId(Integer leoProvinceId) {
        this.leoProvinceId = leoProvinceId;
    }

    public String getLeoProvinceName() {
        return leoProvinceName;
    }

    public void setLeoProvinceName(String leoProvinceName) {
        this.leoProvinceName = leoProvinceName;
    }

}