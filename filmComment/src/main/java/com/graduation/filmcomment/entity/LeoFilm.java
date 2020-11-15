package com.graduation.filmcomment.entity;

import com.graduation.filmcomment.utils.DateUtils;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

public class LeoFilm implements Serializable {
    private static final long serialVersionUID = -30511502007877121L;

    @Id
    private Integer leoFilmId;

    private String leoFilmName;

    private String leoFilmDirector;

    private String leoFilmEditor;

    private String leoFilmActor;

    private String leoFilmCountry;

    private String leoFilmLanguage;

    private String leoFilmReleasedate;

    private String leoFilmLength;

    private String leoFilmCover;

    private String leoFilmTrailer;

    private String leoFilmIntroduce;

    private String leoFilmForm;

    private String leoFilmType;

    private String leoFilmAdmindate;

    private Double leoFilmScore;

    @Transient
    private Integer leoFilmCommentId;

    @Transient
    private String leoFilmCommentContent;

    @Transient
    private String leoFilmCommentScore;

    @Transient
    private String leoFilmCommentStatus;

    @Transient
    private String leoFilmCommentDate;

    public Integer getLeoFilmCommentId() {
        return leoFilmCommentId;
    }

    public void setLeoFilmCommentId(Integer leoFilmCommentId) {
        this.leoFilmCommentId = leoFilmCommentId;
    }

    public Double getLeoFilmScore() {
        return leoFilmScore;
    }

    public String getLeoFilmTrailer() {
        return leoFilmTrailer;
    }

    public void setLeoFilmTrailer(String leoFilmTrailer) {
        this.leoFilmTrailer = leoFilmTrailer;
    }

    public void setLeoFilmScore(Double leoFilmScore) {
        this.leoFilmScore = leoFilmScore;
    }

    public String getLeoFilmCommentContent() {
        return leoFilmCommentContent;
    }

    public void setLeoFilmCommentContent(String leoFilmCommentContent) {
        this.leoFilmCommentContent = leoFilmCommentContent;
    }

    public String getLeoFilmCommentScore() {
        return leoFilmCommentScore;
    }

    public void setLeoFilmCommentScore(String leoFilmCommentScore) {
        this.leoFilmCommentScore = leoFilmCommentScore;
    }

    public String getLeoFilmCommentStatus() {
        return leoFilmCommentStatus;
    }

    public void setLeoFilmCommentStatus(String leoFilmCommentStatus) {
        this.leoFilmCommentStatus = leoFilmCommentStatus;
    }

    public String getLeoFilmCommentDate() {
        return leoFilmCommentDate;
    }

    public void setLeoFilmCommentDate(String leoFilmCommentDate) {
        this.leoFilmCommentDate = leoFilmCommentDate;
    }

    public Integer getLeoFilmId() {
        return leoFilmId;
    }

    public void setLeoFilmId(Integer leoFilmId) {
        this.leoFilmId = leoFilmId;
    }

    public String getLeoFilmName() {
        return leoFilmName;
    }

    public void setLeoFilmName(String leoFilmName) {
        this.leoFilmName = leoFilmName;
    }

    public String getLeoFilmForm() {
        return leoFilmForm;
    }

    public void setLeoFilmForm(String leoFilmForm) {
        this.leoFilmForm = leoFilmForm;
    }

    public String getLeoFilmDirector() {
        return leoFilmDirector;
    }

    public void setLeoFilmDirector(String leoFilmDirector) {
        this.leoFilmDirector = leoFilmDirector;
    }

    public String getLeoFilmEditor() {
        return leoFilmEditor;
    }

    public void setLeoFilmEditor(String leoFilmEditor) {
        this.leoFilmEditor = leoFilmEditor;
    }

    public String getLeoFilmActor() {
        return leoFilmActor;
    }

    public void setLeoFilmActor(String leoFilmActor) {
        this.leoFilmActor = leoFilmActor;
    }

    public String getLeoFilmCountry() {
        return leoFilmCountry;
    }

    public void setLeoFilmCountry(String leoFilmCountry) {
        this.leoFilmCountry = leoFilmCountry;
    }

    public String getLeoFilmLanguage() {
        return leoFilmLanguage;
    }

    public void setLeoFilmLanguage(String leoFilmLanguage) {
        this.leoFilmLanguage = leoFilmLanguage;
    }

    public String getLeoFilmReleasedate() {
        return leoFilmReleasedate;
    }

    public void setLeoFilmReleasedate(String leoFilmReleasedate) {
        this.leoFilmReleasedate = leoFilmReleasedate;
    }

    public String getLeoFilmLength() {
        return leoFilmLength;
    }

    public void setLeoFilmLength(String leoFilmLength) {
        this.leoFilmLength = leoFilmLength;
    }

    public String getLeoFilmCover() {
        return leoFilmCover;
    }

    public void setLeoFilmCover(String leoFilmCover) {
        this.leoFilmCover = leoFilmCover;
    }

    public String getLeoFilmIntroduce() {
        return leoFilmIntroduce;
    }

    public void setLeoFilmIntroduce(String leoFilmIntroduce) {
        this.leoFilmIntroduce = leoFilmIntroduce;
    }

    public String getLeoFilmType() {
        return leoFilmType;
    }

    public void setLeoFilmType(String leoFilmType) {
        this.leoFilmType = leoFilmType;
    }

    public String getLeoFilmAdmindate() {
        return leoFilmAdmindate;
    }

    public void setLeoFilmAdmindate(String leoFilmAdmindate) {
        this.leoFilmAdmindate = leoFilmAdmindate;
    }

    @Override
    public String toString() {
        return "LeoFilm{" +
                "leoFilmId=" + leoFilmId +
                ", leoFilmName='" + leoFilmName + '\'' +
                ", leoFilmDirector='" + leoFilmDirector + '\'' +
                ", leoFilmEditor='" + leoFilmEditor + '\'' +
                ", leoFilmActor='" + leoFilmActor + '\'' +
                ", leoFilmCountry='" + leoFilmCountry + '\'' +
                ", leoFilmLanguage='" + leoFilmLanguage + '\'' +
                ", leoFilmReleasedate='" + leoFilmReleasedate + '\'' +
                ", leoFilmLength='" + leoFilmLength + '\'' +
                ", leoFilmCover='" + leoFilmCover + '\'' +
                ", leoFilmTrailer='" + leoFilmTrailer + '\'' +
                ", leoFilmIntroduce='" + leoFilmIntroduce + '\'' +
                ", leoFilmForm='" + leoFilmForm + '\'' +
                ", leoFilmType='" + leoFilmType + '\'' +
                ", leoFilmAdmindate='" + leoFilmAdmindate + '\'' +
                ", leoFilmScore=" + leoFilmScore +
                ", leoFilmCommentContent='" + leoFilmCommentContent + '\'' +
                ", leoFilmCommentScore='" + leoFilmCommentScore + '\'' +
                ", leoFilmCommentStatus='" + leoFilmCommentStatus + '\'' +
                ", leoFilmCommentDate='" + leoFilmCommentDate + '\'' +
                '}';
    }
}