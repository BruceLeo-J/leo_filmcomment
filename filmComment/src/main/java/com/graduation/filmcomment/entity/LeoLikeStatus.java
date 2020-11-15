package com.graduation.filmcomment.entity;

import java.io.Serializable;

/**
 * (LeoLikeStatus)实体类
 *
 * @author makejava
 * @since 2020-10-30 11:05:27
 */
public class LeoLikeStatus implements Serializable {
    private static final long serialVersionUID = 550512730907789086L;

    private Integer leoUserId;

    private Integer leoJournalId;

    private Boolean leoLikeStatus;


    public Integer getLeoUserId() {
        return leoUserId;
    }

    public void setLeoUserId(Integer leoUserId) {
        this.leoUserId = leoUserId;
    }

    public Integer getLeoJournalId() {
        return leoJournalId;
    }

    public void setLeoJournalId(Integer leoJournalId) {
        this.leoJournalId = leoJournalId;
    }

    public Boolean getLeoLikeStatus() {
        return leoLikeStatus;
    }

    public void setLeoLikeStatus(Boolean leoLikeStatus) {
        this.leoLikeStatus = leoLikeStatus;
    }

}