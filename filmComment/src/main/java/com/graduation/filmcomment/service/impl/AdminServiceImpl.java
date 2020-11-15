package com.graduation.filmcomment.service.impl;

import com.graduation.filmcomment.entity.LeoAdmin;
import com.graduation.filmcomment.mapper.AdminMapper;
import com.graduation.filmcomment.service.AdminService;
import com.graduation.filmcomment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void save(LeoAdmin leoAdmin) {
        adminMapper.insert(leoAdmin);
    }

    @Override
    public LeoAdmin findUserByUserName(String principal) {
        return adminMapper.findUserByUserName(principal);
    }

    @Override
    public LeoAdmin findRolesById(Integer leoAdminId) {
        return adminMapper.findRolesById(leoAdminId);
    }

    @Override
    public List<LeoAdmin> findAll() {
        List<LeoAdmin> leoAdminList = adminMapper.findAll();
        return leoAdminList;
    }


}
