package com.graduation.filmcomment.entity;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * (LeoFilmType)实体类
 *
 * @author makejava
 * @since 2020-09-30 19:43:12
 */
public class LeoFilmType implements Serializable {
    private static final long serialVersionUID = -70189422907771702L;

    private Integer leoFilmTypeId;

    private String leoFilmTypeName;

    private String leoFilmTypeCode;

    public String getLeoFilmTypeCode() {
        return leoFilmTypeCode;
    }

    public void setLeoFilmTypeCode(String leoFilmTypeCode) {
        this.leoFilmTypeCode = leoFilmTypeCode;
    }

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


    @Override
    public String toString() {
        return "LeoFilmType{" +
                "leoFilmTypeId=" + leoFilmTypeId +
                ", leoFilmTypeName='" + leoFilmTypeName + '\'' +
                '}';
    }
}