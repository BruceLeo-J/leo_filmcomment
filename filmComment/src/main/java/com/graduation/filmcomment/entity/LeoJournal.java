package com.graduation.filmcomment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (LeoJournal)实体类
 *
 * @author makejava
 * @since 2020-10-29 16:18:43
 */
public class LeoJournal implements Serializable {
    private static final long serialVersionUID = 152266283122969009L;

    private Integer leoJournalId;

    private String leoJournalTitle;

    private String leoJournalContent;

    private Integer leoJournalLike;

    private Date leoJournalDate;


    public Integer getLeoJournalId() {
        return leoJournalId;
    }

    public void setLeoJournalId(Integer leoJournalId) {
        this.leoJournalId = leoJournalId;
    }

    public String getLeoJournalTitle() {
        return leoJournalTitle;
    }

    public void setLeoJournalTitle(String leoJournalTitle) {
        this.leoJournalTitle = leoJournalTitle;
    }

    public String getLeoJournalContent() {
        return leoJournalContent;
    }

    public void setLeoJournalContent(String leoJournalContent) {
        this.leoJournalContent = leoJournalContent;
    }

    public Integer getLeoJournalLike() {
        return leoJournalLike;
    }

    public void setLeoJournalLike(Integer leoJournalLike) {
        this.leoJournalLike = leoJournalLike;
    }

    public Date getLeoJournalDate() {
        return leoJournalDate;
    }

    public void setLeoJournalDate(Date leoJournalDate) {
        this.leoJournalDate = leoJournalDate;
    }

    @Override
    public String toString() {
        return "LeoJournal{" +
                "leoJournalId=" + leoJournalId +
                ", leoJournalTitle='" + leoJournalTitle + '\'' +
                ", leoJournalContent='" + leoJournalContent + '\'' +
                ", leoJournalLike=" + leoJournalLike +
                ", leoJournalDate=" + leoJournalDate +
                '}';
    }
}