package com.graduation.filmcomment.mapper;

import com.graduation.filmcomment.entity.LeoCity;
import com.graduation.filmcomment.entity.LeoProvince;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProvinceMapper extends tk.mybatis.mapper.common.Mapper<LeoProvince> {

    @Select("select * from leo_province")
    List<LeoProvince> findProvince();

    @Select("select * from leo_city where leo_city_province_id = #{provinceId}")
    List<LeoCity> findCity(int provinceId);
}
