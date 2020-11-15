package com.graduation.filmcomment.entity;

import java.io.Serializable;

/**
 * (LeoFilmLanguage)实体类
 *
 * @author makejava
 * @since 2020-10-14 20:09:10
 */
public class LeoFilmLanguage implements Serializable {
    private static final long serialVersionUID = -70488630884850482L;

    private Integer leoFilmLanguageId;

    private String leoFilmLanguageName;

    private Integer leoFilmLanguageCode;


    public Integer getLeoFilmLanguageId() {
        return leoFilmLanguageId;
    }

    public void setLeoFilmLanguageId(Integer leoFilmLanguageId) {
        this.leoFilmLanguageId = leoFilmLanguageId;
    }

    public String getLeoFilmLanguageName() {
        return leoFilmLanguageName;
    }

    public void setLeoFilmLanguageName(String leoFilmLanguageName) {
        this.leoFilmLanguageName = leoFilmLanguageName;
    }

    public Integer getLeoFilmLanguageCode() {
        return leoFilmLanguageCode;
    }

    public void setLeoFilmLanguageCode(Integer leoFilmLanguageCode) {
        this.leoFilmLanguageCode = leoFilmLanguageCode;
    }
}