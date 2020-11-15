package com.graduation.filmcomment.entity;

import java.io.Serializable;

/**
 * (LeoFilmAllType)实体类
 *
 * @author makejava
 * @since 2020-10-28 19:01:31
 */
public class LeoFilmAllType implements Serializable {
    private static final long serialVersionUID = 168820245148593756L;

    private Integer leoFilmTypeId;

    private String leoFilmTypeName;


    public Integer getLeoFilmTypeId() {
        return leoFilmTypeId;
    }

    public void setLeoFilmTypeId(Integer leoFilmTypeId) {
        this.leoFilmTypeId = leoFilmTypeId;
    }

    public String getLeoFilmTypeName() {
        return leoFilmTypeName;
    }

    public void setLeoFilmTypeName(String leoFilmTypeName) {
        this.leoFilmTypeName = leoFilmTypeName;
    }

}