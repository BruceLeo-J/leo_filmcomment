package com.graduation.filmcomment.service;

import com.graduation.filmcomment.entity.LeoAdmin;

import java.util.List;

public interface AdminService {
    void save(LeoAdmin leoAdmin);

    LeoAdmin findUserByUserName(String principal);

    LeoAdmin findRolesById(Integer leoAdminId);

    List<LeoAdmin> findAll();
}
