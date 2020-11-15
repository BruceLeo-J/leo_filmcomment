package com.graduation.filmcomment.mapper;

import com.graduation.filmcomment.entity.LeoAdmin;
import com.graduation.filmcomment.entity.LeoRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper extends tk.mybatis.mapper.common.Mapper<LeoAdmin> {

    @Select("select * from leo_admin where leo_admin_username = #{principal}")
    LeoAdmin findUserByUserName(String principal);

    @Select("SELECT * FROM leo_admin where leo_admin_username = #{primaryPrincipal}")
    LeoAdmin findRolesByUserName(String primaryPrincipal);

    @Select("select * from leo_admin where leo_admin_id = #{leoAdminId}")
    @Results({
            @Result(id = true, column = "leo_admin_id", property = "leoAdminId"),
            @Result(column = "leo_admin_username", property = "leoAdminUsername"),
            @Result(column = "leo_admin_password", property = "leoAdminPassword"),
            @Result(column = "leo_admin_salt", property = "leoAdminSalt"),
            @Result(column = "leo_admin_id", property = "leoRoles",many = @Many(select = "com.graduation.filmcomment.mapper.RoleMapper.findById"))
    })
    LeoAdmin findRolesById(Integer leoAdminId);

    @Select("select * from leo_admin ")
    @Results({
            @Result(id = true, column = "leo_admin_id", property = "leoAdminId"),
            @Result(column = "leo_admin_username", property = "leoAdminUsername"),
            @Result(column = "leo_admin_password", property = "leoAdminPassword"),
            @Result(column = "leo_admin_salt", property = "leoAdminSalt"),
            @Result(column = "leo_admin_phone", property = "leoAdminPhone"),
            @Result(column = "leo_admin_email", property = "leoAdminEmail"),
            @Result(column = "leo_admin_entry_date", property = "leoAdminEntryDate"),
            @Result(column = "leo_admin_id", property = "leoRoles",many = @Many(select = "com.graduation.filmcomment.mapper.RoleMapper.findById"))
    })
    List<LeoAdmin> findAll();
}
