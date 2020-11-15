package com.graduation.filmcomment.entity;

import java.io.Serializable;

/**
 * (LeoFilmForm)实体类
 *
 * @author makejava
 * @since 2020-10-13 03:42:40
 */
public class LeoFilmForm implements Serializable {
    private static final long serialVersionUID = -20802159412992260L;

    private Integer leoFilmFormId;

    private String leoFilmFormName;

    private Integer leoFilmFormCode;


    public Integer getLeoFilmFormId() {
        return leoFilmFormId;
    }

    public void setLeoFilmFormId(Integer leoFilmFormId) {
        this.leoFilmFormId = leoFilmFormId;
    }

    public String getLeoFilmFormName() {
        return leoFilmFormName;
    }

    public void setLeoFilmFormName(String leoFilmFormName) {
        this.leoFilmFormName = leoFilmFormName;
    }

    public Integer getLeoFilmFormCode() {
        return leoFilmFormCode;
    }

    public void setLeoFilmFormCode(Integer leoFilmFormCode) {
        this.leoFilmFormCode = leoFilmFormCode;
    }

    @Override
    public String toString() {
        return "LeoFilmForm{" +
                "leoFilmFormId=" + leoFilmFormId +
                ", leoFilmFormName='" + leoFilmFormName + '\'' +
                '}';
    }
}