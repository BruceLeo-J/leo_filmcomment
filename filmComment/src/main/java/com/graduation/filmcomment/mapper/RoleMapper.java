package com.graduation.filmcomment.mapper;

import com.graduation.filmcomment.entity.LeoRole;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface RoleMapper extends tk.mybatis.mapper.common.Mapper<LeoRole> {

    @Select("select * from leo_role where leo_role_id in (select leo_role_id from leo_role_admin where leo_admin_id=#{leoAdminId})")
    @Results({
    @Result(id = true,column = "leo_role_id",property = "leoRoleId"),
    @Result(column = "leo_role_name",property = "leoRoleName"),
    @Result(column = "leo_role_id",property = "leoPermissions",javaType = java.util.List.class,many = @Many(select = "com.graduation.filmcomment.mapper.PermissionMapper.findById"))})
    List<LeoRole> findById(Integer leoAdminId);
}

