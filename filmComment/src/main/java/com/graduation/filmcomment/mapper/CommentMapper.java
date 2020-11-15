package com.graduation.filmcomment.mapper;


import com.graduation.filmcomment.entity.LeoAgreeStatus;
import com.graduation.filmcomment.entity.LeoFilm;
import com.graduation.filmcomment.entity.LeoFilmComment;
import com.graduation.filmcomment.entity.LeoUser;
import org.apache.ibatis.annotations.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface CommentMapper extends tk.mybatis.mapper.common.Mapper<LeoFilmComment>{

    @Insert("insert into leo_film_comment(leo_film_comment.leo_film_comment_status,leo_film_comment.leo_film_comment_film_id,leo_film_comment.leo_film_comment_user_id,leo_film_comment.leo_film_comment_content,leo_film_comment.leo_film_comment_score,leo_film_comment.leo_film_comment_agree)\n" +
            "VALUES(#{leoFilmCommentStatus},#{leoFilm.leoFilmId},#{leoUser.leoUserId},#{leoFilmCommentContent},#{leoFilmCommentScore},#{leoFilmCommentAgree});")
    void insertComment(LeoFilmComment leoFilmComment);

    @Select("select * from leo_film where leo_film_id = #{id}")
    LeoFilm findFilmById(Integer id);

    @Select("SELECT * FROM `leo_film_comment` a\n" +
            "LEFT JOIN leo_user b ON a.leo_film_comment_user_id=b.leo_user_id\n" +
            "LEFT JOIN leo_film c ON a.leo_film_comment_film_id=c.leo_film_id")
    List<LeoFilmComment> commentSelectAll();

    @Select("select * from leo_film_comment where leo_film_comment_id=#{leoFilmCommentId}")
    LeoFilmComment findCommentByCommentId(Integer leoFilmCommentId);

    @Select("select * from leo_agree_status where leo_user_id=#{leoUserId} and leo_comment_id=#{leoFilmCommentId}")
    LeoAgreeStatus checkAgreeStatus(Integer leoUserId, Integer leoFilmCommentId);

    @Update("update leo_film_comment set leo_film_comment_agree = leo_film_comment_agree + #{result} where leo_film_comment_id = #{leoFilmCommentId} ")
    void updateAgreeByCommentId(Integer leoFilmCommentId,Integer result);

    @Update("update leo_agree_status set leo_agree_status = #{b} where leo_user_id = #{leoUserId} and leo_comment_id=#{leoFilmCommentId}")
    void updateAgreeStatus(Integer leoUserId, Integer leoFilmCommentId, boolean b);

    @Insert("insert into leo_agree_status values(#{leoUserId},#{leoFilmCommentId},#{b})")
    void insertUserAgreeStatus(Integer leoUserId, Integer leoFilmCommentId, boolean b);

    @Delete("delete from leo_film_comment where leo_film_comment_id=#{leoFilmCommentId}")
    void deleteCommentById(Integer leoFilmCommentId);

    @Select("select * from leo_film_comment where leo_film_comment_film_id = #{leoFilmId} and leo_film_comment_user_id = #{leoUserId}")
    LeoFilmComment findCommentByFilmAndUserId(Integer leoFilmId, Integer leoUserId);
}
