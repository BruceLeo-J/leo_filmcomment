package com.graduation.filmcomment.service;

import com.graduation.filmcomment.entity.LeoCity;
import com.graduation.filmcomment.entity.LeoProvince;

import java.util.List;


public interface ProvinceService {
    //查询所有省份
    public List<LeoProvince> findProvince();
    //根据省份code查找市级
    public List<LeoCity> findCity(int provinceId);
}
