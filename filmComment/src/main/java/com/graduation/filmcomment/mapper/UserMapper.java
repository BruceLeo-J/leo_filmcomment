package com.graduation.filmcomment.mapper;

import com.graduation.filmcomment.entity.LeoUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<LeoUser> {

    @Insert("INSERT INTO leo_user(leo_user_name,leo_user_password,leo_user_phone,leo_user_email,leo_user_image,leo_user_province_id,leo_user_city_id) VALUES(#{leoUserName},#{leoUserPassword},#{leoUserPhone},#{leoUserEmail},'/img/user_default.jpg',1,1)")
    void register(LeoUser leoUser);

    @Select("select * from leo_user where leo_user_phone=#{leoUserPhone} and leo_user_password=#{leoUserPassword}")
    LeoUser loginUser(LeoUser leoUser);

    @Select("select * from leo_user where leo_user_id=#{id}")
    LeoUser findUserById(Integer id);

    @Update("update leo_user set leo_user_status = #{leoUserStatus} where leo_user_id = #{leoUserId}")
    void updateUserStatus(Integer leoUserId, Boolean leoUserStatus);

    @Select("select * from leo_user where leo_user_name = #{leoUserName}")
    LeoUser isExistUserName(String leoUserName);

    @Select("select * from leo_user where leo_user_phone = #{leoUserPhone}")
    LeoUser isExistUserPhone(String leoUserPhone);

    @Select("select * from leo_user where leo_user_email = #{leoUserEmail}")
    LeoUser isExistUserEmail(String leoUserEmail);

    @Update("update leo_user set leo_user_image=#{uuidFileName} where leo_user_id=#{leoUserId}")
    void updateUserImage(Integer leoUserId, String uuidFileName);

    @Update("update leo_user set leo_user_name=#{leoUserName},leo_user_province_id=#{leoUserProvinceId},leo_user_city_id=#{leoUserCityId} where leo_user_id=#{leoUserId}")
    void modifyUserInfo(LeoUser leoUser);

    @Select("select * from leo_user where leo_user_phone = #{loginPhone}")
    LeoUser findUserByPhone(String loginPhone);
}
