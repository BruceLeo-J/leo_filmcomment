package com.graduation.filmcomment.entity;

import java.io.Serializable;

/**
 * (LeoAgreeStatus)实体类
 *
 * @author makejava
 * @since 2020-10-26 14:44:57
 */
public class LeoAgreeStatus implements Serializable {
    private static final long serialVersionUID = 311257721627410492L;

    private Integer leoUserId;

    private Integer leoCommentId;

    private Boolean leoAgreeStatus;


    public Integer getLeoUserId() {
        return leoUserId;
    }

    public void setLeoUserId(Integer leoUserId) {
        this.leoUserId = leoUserId;
    }

    public Integer getLeoCommentId() {
        return leoCommentId;
    }

    public void setLeoCommentId(Integer leoCommentId) {
        this.leoCommentId = leoCommentId;
    }


    public Boolean getLeoAgreeStatus() {
        return leoAgreeStatus;
    }

    public void setLeoAgreeStatus(Boolean leoAgreeStatus) {
        this.leoAgreeStatus = leoAgreeStatus;
    }
}