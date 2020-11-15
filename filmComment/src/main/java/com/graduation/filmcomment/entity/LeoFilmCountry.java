package com.graduation.filmcomment.entity;

import java.io.Serializable;

/**
 * (LeoFilmCountry)实体类
 *
 * @author makejava
 * @since 2020-10-14 18:56:46
 */
public class LeoFilmCountry implements Serializable {
    private static final long serialVersionUID = 945732877105757209L;

    private Integer leoFilmCountryId;

    private String leoFilmCountryName;

    private Integer leoFilmCountryCode;


    public Integer getLeoFilmCountryId() {
        return leoFilmCountryId;
    }

    public void setLeoFilmCountryId(Integer leoFilmCountryId) {
        this.leoFilmCountryId = leoFilmCountryId;
    }

    public String getLeoFilmCountryName() {
        return leoFilmCountryName;
    }

    public void setLeoFilmCountryName(String leoFilmCountryName) {
        this.leoFilmCountryName = leoFilmCountryName;
    }

    public Integer getLeoFilmCountryCode() {
        return leoFilmCountryCode;
    }

    public void setLeoFilmCountryCode(Integer leoFilmCountryCode) {
        this.leoFilmCountryCode = leoFilmCountryCode;
    }

    @Override
    public String toString() {
        return "LeoFilmCountry{" +
                "leoFilmCountryId=" + leoFilmCountryId +
                ", leoFilmCountryName='" + leoFilmCountryName + '\'' +
                '}';
    }
}