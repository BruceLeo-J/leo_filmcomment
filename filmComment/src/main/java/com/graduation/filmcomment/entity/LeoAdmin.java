package com.graduation.filmcomment.entity;

import java.io.Serializable;
import java.util.List;

/**
 * (LeoAdmin)实体类
 *
 * @author makejava
 * @since 2020-11-01 16:41:10
 */
public class LeoAdmin implements Serializable {
    private static final long serialVersionUID = 416278282115253525L;

    private Integer leoAdminId;

    private String leoAdminUsername;

    private String leoAdminPassword;

    private String leoAdminSalt;

    private String leoAdminPhone;

    private String leoAdminEmail;

    private String leoAdminEntryDate;

    private List<LeoRole> leoRoles;


    public Integer getLeoAdminId() {
        return leoAdminId;
    }

    public void setLeoAdminId(Integer leoAdminId) {
        this.leoAdminId = leoAdminId;
    }

    public String getLeoAdminUsername() {
        return leoAdminUsername;
    }

    public void setLeoAdminUsername(String leoAdminUsername) {
        this.leoAdminUsername = leoAdminUsername;
    }

    public String getLeoAdminPassword() {
        return leoAdminPassword;
    }

    public void setLeoAdminPassword(String leoAdminPassword) {
        this.leoAdminPassword = leoAdminPassword;
    }

    public String getLeoAdminSalt() {
        return leoAdminSalt;
    }

    public void setLeoAdminSalt(String leoAdminSalt) {
        this.leoAdminSalt = leoAdminSalt;
    }

    public String getLeoAdminPhone() {
        return leoAdminPhone;
    }

    public void setLeoAdminPhone(String leoAdminPhone) {
        this.leoAdminPhone = leoAdminPhone;
    }

    public String getLeoAdminEmail() {
        return leoAdminEmail;
    }

    public void setLeoAdminEmail(String leoAdminEmail) {
        this.leoAdminEmail = leoAdminEmail;
    }

    public String getLeoAdminEntryDate() {
        return leoAdminEntryDate;
    }

    public void setLeoAdminEntryDate(String leoAdminEntryDate) {
        this.leoAdminEntryDate = leoAdminEntryDate;
    }

    public List<LeoRole> getLeoRoles() {
        return leoRoles;
    }

    public void setLeoRoles(List<LeoRole> leoRoles) {
        this.leoRoles = leoRoles;
    }

    @Override
    public String toString() {
        return "LeoAdmin{" +
                "leoAdminId=" + leoAdminId +
                ", leoAdminUsername='" + leoAdminUsername + '\'' +
                ", leoAdminPassword='" + leoAdminPassword + '\'' +
                ", leoAdminSalt='" + leoAdminSalt + '\'' +
                ", leoAdminPhone='" + leoAdminPhone + '\'' +
                ", leoAdminEmail='" + leoAdminEmail + '\'' +
                ", leoAdminEntryDate='" + leoAdminEntryDate + '\'' +
                ", leoRoles=" + leoRoles +
                '}';
    }
}