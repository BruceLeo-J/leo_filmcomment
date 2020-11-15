package com.graduation.filmcomment.service.impl;

import com.graduation.filmcomment.entity.LeoCity;
import com.graduation.filmcomment.entity.LeoProvince;
import com.graduation.filmcomment.mapper.ProvinceMapper;
import com.graduation.filmcomment.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public List<LeoProvince> findProvince() {
        return provinceMapper.findProvince();
    }

    @Override
    public List<LeoCity> findCity(int provinceId) {
        return provinceMapper.findCity(provinceId);
    }
}
