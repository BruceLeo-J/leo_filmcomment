package com.graduation.filmcomment.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * (LeoUser)实体类
 *
 * @author makejava
 * @since 2020-10-21 17:44:27
 */
public class LeoUser implements Serializable {
    private static final long serialVersionUID = -91732555659987886L;

    @Id
    private Integer leoUserId;

    private String leoUserName;

    private String leoUserPassword;

    private String leoUserImage;

    private String leoUserPhone;

    private String leoUserEmail;

    private String leoUserProvinceId;

    private String leoUserCityId;

    private Boolean leoUserStatus;

    public Boolean getLeoUserStatus() {
        return leoUserStatus;
    }

    public void setLeoUserStatus(Boolean leoUserStatus) {
        this.leoUserStatus = leoUserStatus;
    }

    public Integer getLeoUserId() {
        return leoUserId;
    }

    public void setLeoUserId(Integer leoUserId) {
        this.leoUserId = leoUserId;
    }

    public String getLeoUserName() {
        return leoUserName;
    }

    public void setLeoUserName(String leoUserName) {
        this.leoUserName = leoUserName;
    }

    public String getLeoUserPassword() {
        return leoUserPassword;
    }

    public void setLeoUserPassword(String leoUserPassword) {
        this.leoUserPassword = leoUserPassword;
    }

    public String getLeoUserImage() {
        return leoUserImage;
    }

    public void setLeoUserImage(String leoUserImage) {
        this.leoUserImage = leoUserImage;
    }

    public String getLeoUserPhone() {
        return leoUserPhone;
    }

    public void setLeoUserPhone(String leoUserPhone) {
        this.leoUserPhone = leoUserPhone;
    }

    public String getLeoUserEmail() {
        return leoUserEmail;
    }

    public void setLeoUserEmail(String leoUserEmail) {
        this.leoUserEmail = leoUserEmail;
    }

    public String getLeoUserProvinceId() {
        return leoUserProvinceId;
    }

    public void setLeoUserProvinceId(String leoUserProvinceId) {
        this.leoUserProvinceId = leoUserProvinceId;
    }

    public String getLeoUserCityId() {
        return leoUserCityId;
    }

    public void setLeoUserCityId(String leoUserCityId) {
        this.leoUserCityId = leoUserCityId;
    }

    @Override
    public String toString() {
        return "LeoUser{" +
                "leoUserId=" + leoUserId +
                ", leoUserName='" + leoUserName + '\'' +
                ", leoUserPassword='" + leoUserPassword + '\'' +
                ", leoUserImage='" + leoUserImage + '\'' +
                ", leoUserPhone='" + leoUserPhone + '\'' +
                ", leoUserEmail='" + leoUserEmail + '\'' +
                ", leoUserProvinceId='" + leoUserProvinceId + '\'' +
                ", leoUserCityId='" + leoUserCityId + '\'' +
                '}';
    }
}