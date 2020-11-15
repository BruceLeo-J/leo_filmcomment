package com.graduation.filmcomment.controller;

import com.graduation.filmcomment.entity.LeoCity;
import com.graduation.filmcomment.entity.LeoProvince;
import com.graduation.filmcomment.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @RequestMapping("/findProvince")
    @ResponseBody
    public List<LeoProvince> findProvince(){
        List<LeoProvince> provinceList=provinceService.findProvince();
        return provinceList;
    }

    @RequestMapping("/findCity")
    public List<LeoCity> findCity(Integer provinceId){
        List<LeoCity> cityList=provinceService.findCity(provinceId);
        return cityList;
    }

}