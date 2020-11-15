package com.graduation.filmcomment.entity;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * (LeoFilmComment)实体类
 *
 * @author makejava
 * @since 2020-10-02 13:04:14
 */
public class LeoFilmComment implements Serializable {
    private static final long serialVersionUID = -25755503890873099L;

    @Id
    private Integer leoFilmCommentId;

    private Integer leoFilmCommentFilmId;

    private Integer leoFilmCommentUserId;

    private LeoUser leoUser;

    private LeoFilm leoFilm;

    private String leoFilmCommentContent;

    private Integer leoFilmCommentScore;

    private Integer leoFilmCommentAgree;

    private String leoFilmCommentStatus;

    private Timestamp leoFilmCommentDate;

    @Transient
    private String leoUserId;

    @Transient
    private String leoUserName;

    @Transient
    private String leoUserImage;

    @Transient
    private String leoFilmId;

    @Transient
    private String leoFilmName;


    public Integer getLeoFilmCommentUserId() {
        return leoFilmCommentUserId;
    }

    public void setLeoFilmCommentUserId(Integer leoFilmCommentUserId) {
        this.leoFilmCommentUserId = leoFilmCommentUserId;
    }

    public String getLeoUserId() {
        return leoUserId;
    }

    public void setLeoUserId(String leoUserId) {
        this.leoUserId = leoUserId;
    }

    public String getLeoFilmId() {
        return leoFilmId;
    }

    public void setLeoFilmId(String leoFilmId) {
        this.leoFilmId = leoFilmId;
    }

    public String getLeoFilmName() {
        return leoFilmName;
    }

    public void setLeoFilmName(String leoFilmName) {
        this.leoFilmName = leoFilmName;
    }

    public String getLeoUserName() {
        return leoUserName;
    }

    public void setLeoUserName(String leoUserName) {
        this.leoUserName = leoUserName;
    }

    public String getLeoUserImage() {
        return leoUserImage;
    }

    public void setLeoUserImage(String leoUserImage) {
        this.leoUserImage = leoUserImage;
    }

    public LeoFilm getLeoFilm() {
        return leoFilm;
    }

    public void setLeoFilm(LeoFilm leoFilm) {
        this.leoFilm = leoFilm;
    }

    public Integer getLeoFilmCommentId() {
        return leoFilmCommentId;
    }

    public void setLeoFilmCommentId(Integer leoFilmCommentId) {
        this.leoFilmCommentId = leoFilmCommentId;
    }

    public Integer getLeoFilmCommentFilmId() {
        return leoFilmCommentFilmId;
    }

    public void setLeoFilmCommentFilmId(Integer leoFilmCommentFilmId) {
        this.leoFilmCommentFilmId = leoFilmCommentFilmId;
    }

    public LeoUser getLeoUser() {
        return leoUser;
    }

    public void setLeoUser(LeoUser leoUser) {
        this.leoUser = leoUser;
    }

    public String getLeoFilmCommentContent() {
        return leoFilmCommentContent;
    }

    public void setLeoFilmCommentContent(String leoFilmCommentContent) {
        this.leoFilmCommentContent = leoFilmCommentContent;
    }

    public Integer getLeoFilmCommentScore() {
        return leoFilmCommentScore;
    }

    public void setLeoFilmCommentScore(Integer leoFilmCommentScore) {
        this.leoFilmCommentScore = leoFilmCommentScore;
    }

    public Integer getLeoFilmCommentAgree() {
        return leoFilmCommentAgree;
    }

    public void setLeoFilmCommentAgree(Integer leoFilmCommentAgree) {
        this.leoFilmCommentAgree = leoFilmCommentAgree;
    }

    public String getLeoFilmCommentStatus() {
        return leoFilmCommentStatus;
    }

    public void setLeoFilmCommentStatus(String leoFilmCommentStatus) {
        this.leoFilmCommentStatus = leoFilmCommentStatus;
    }

    public Timestamp getLeoFilmCommentDate() {
        return leoFilmCommentDate;
    }

    public void setLeoFilmCommentDate(Timestamp leoFilmCommentDate) {
        this.leoFilmCommentDate = leoFilmCommentDate;
    }

    @Override
    public String toString() {
        return "LeoFilmComment{" +
                "leoFilmCommentId=" + leoFilmCommentId +
                ", leoFilmCommentFilmId=" + leoFilmCommentFilmId +
                ", leoFilmCommentUserId=" + leoFilmCommentUserId +
                ", leoUser=" + leoUser +
                ", leoFilm=" + leoFilm +
                ", leoFilmCommentContent='" + leoFilmCommentContent + '\'' +
                ", leoFilmCommentScore=" + leoFilmCommentScore +
                ", leoFilmCommentAgree=" + leoFilmCommentAgree +
                ", leoFilmCommentStatus='" + leoFilmCommentStatus + '\'' +
                ", leoFilmCommentDate=" + leoFilmCommentDate +
                ", leoUserId='" + leoUserId + '\'' +
                ", leoUserName='" + leoUserName + '\'' +
                ", leoUserImage='" + leoUserImage + '\'' +
                ", leoFilmId='" + leoFilmId + '\'' +
                ", leoFilmName='" + leoFilmName + '\'' +
                '}';
    }
}