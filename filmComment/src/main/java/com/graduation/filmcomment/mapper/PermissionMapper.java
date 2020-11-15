package com.graduation.filmcomment.mapper;

import com.graduation.filmcomment.entity.LeoPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PermissionMapper extends tk.mybatis.mapper.common.Mapper<LeoPermission> {

    @Select("select * from leo_permission where leo_permission_id in (select leo_permission_id from leo_permission_role where leo_role_id=#{leoRoleId})")
    LeoPermission findById(Integer leoRoleId);
}
