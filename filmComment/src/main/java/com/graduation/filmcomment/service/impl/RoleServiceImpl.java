package com.graduation.filmcomment.service.impl;

import com.graduation.filmcomment.entity.LeoAdmin;
import com.graduation.filmcomment.entity.LeoRole;
import com.graduation.filmcomment.mapper.RoleMapper;
import com.graduation.filmcomment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;




}
