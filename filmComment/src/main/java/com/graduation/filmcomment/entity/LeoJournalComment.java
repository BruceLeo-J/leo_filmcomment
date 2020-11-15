package com.graduation.filmcomment.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * (LeoJournalComment)实体类
 *
 * @author makejava
 * @since 2020-11-02 21:51:08
 */
public class LeoJournalComment implements Serializable {
    private static final long serialVersionUID = 150015156821082845L;

    private Integer leoJournalCommentId;

    private Integer leoJournalCommentParentId;

    private Integer leoJournalCommentLevel;

    private Integer leoJournalId;

    private Integer leoUserId;

    private String leoJournalCommentContent;

    private Timestamp leoJournalCommentDate;

    private LeoUser leoUser;


    public Integer getLeoJournalCommentId() {
        return leoJournalCommentId;
    }

    public void setLeoJournalCommentId(Integer leoJournalCommentId) {
        this.leoJournalCommentId = leoJournalCommentId;
    }

    public Integer getLeoJournalCommentParentId() {
        return leoJournalCommentParentId;
    }

    public void setLeoJournalCommentParentId(Integer leoJournalCommentParentId) {
        this.leoJournalCommentParentId = leoJournalCommentParentId;
    }

    public Integer getLeoJournalCommentLevel() {
        return leoJournalCommentLevel;
    }

    public void setLeoJournalCommentLevel(Integer leoJournalCommentLevel) {
        this.leoJournalCommentLevel = leoJournalCommentLevel;
    }

    public Integer getLeoJournalId() {
        return leoJournalId;
    }

    public void setLeoJournalId(Integer leoJournalId) {
        this.leoJournalId = leoJournalId;
    }

    public Integer getLeoUserId() {
        return leoUserId;
    }

    public void setLeoUserId(Integer leoUserId) {
        this.leoUserId = leoUserId;
    }

    public String getLeoJournalCommentContent() {
        return leoJournalCommentContent;
    }

    public void setLeoJournalCommentContent(String leoJournalCommentContent) {
        this.leoJournalCommentContent = leoJournalCommentContent;
    }

    public Timestamp getLeoJournalCommentDate() {
        return leoJournalCommentDate;
    }

    public void setLeoJournalCommentDate(Timestamp leoJournalCommentDate) {
        this.leoJournalCommentDate = leoJournalCommentDate;
    }

    public LeoUser getLeoUser() {
        return leoUser;
    }

    public void setLeoUser(LeoUser leoUser) {
        this.leoUser = leoUser;
    }

    @Override
    public String toString() {
        return "LeoJournalComment{" +
                "leoJournalCommentId=" + leoJournalCommentId +
                ", leoJournalCommentParentId=" + leoJournalCommentParentId +
                ", leoJournalCommentLevel=" + leoJournalCommentLevel +
                ", leoJournalId=" + leoJournalId +
                ", leoUserId=" + leoUserId +
                ", leoJournalCommentContent='" + leoJournalCommentContent + '\'' +
                ", leoJournalCommentDate=" + leoJournalCommentDate +
                ", leoUser=" + leoUser +
                '}';
    }
}