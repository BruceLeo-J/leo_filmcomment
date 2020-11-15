package com.graduation.filmcomment.service;

import com.github.pagehelper.PageInfo;
import com.graduation.filmcomment.entity.LeoFilm;
import com.graduation.filmcomment.entity.LeoUser;

import java.util.List;

public interface UserService {

    void register(LeoUser leoUser);

    LeoUser loginUser(LeoUser leoUser);

    LeoUser findUserById(Integer id);

    List<LeoFilm> findFiveWantSeeFilmByUserId(Integer id);

    List<LeoFilm> findFiveSeenFilmByUserId(Integer id);

    PageInfo<LeoUser> findAll(Integer page,Integer limit,LeoUser leoUser);

    void updateUserStatus(Integer leoUserId, Boolean leoUserStatus);

    LeoUser isExistUserName(String leoUserName);

    LeoUser isExistUserPhone(String leoUserPhone);

    LeoUser isExistUserEmail(String leoUserEmail);

    void updateUserImage(Integer leoUserId, String uuidFileName);

    void modifyUserInfo(LeoUser leoUser);

    LeoUser findUserByPhone(String loginPhone);
}
