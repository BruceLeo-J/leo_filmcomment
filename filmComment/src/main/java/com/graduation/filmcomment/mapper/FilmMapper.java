package com.graduation.filmcomment.mapper;

import com.graduation.filmcomment.entity.LeoFilm;
import com.graduation.filmcomment.entity.LeoFilmComment;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface FilmMapper extends Mapper<LeoFilm> {


    @Select("select * from leo_film  where SUBSTRING(leo_film_releasedate,1,10) > DATE_SUB(curdate(),INTERVAL 1 month) order by SUBSTRING(leo_film_releasedate,1,10) desc LIMIT 5")
    List<LeoFilm> recentlyRelease();

    /**
     * CONVERT(value, type);
     * 注：
     * 　　这里的type可以为：
     * 　　　　浮点数 : DECIMAL
     * 　　　　整数 : SIGNED
     * 　　　　无符号整数 : UNSIGNED
     */
//   @Select("select * from leo_film  where SUBSTRING(leo_film_releasedate,1,10) > DATE_SUB(curdate(),INTERVAL 2 year) order by convert(leo_film_commentcount,UNSIGNED) desc LIMIT 10")
    @Select("select * from leo_film  where SUBSTRING(leo_film_releasedate,1,10) > DATE_SUB(curdate(),INTERVAL 2 year)  LIMIT 10")
    List<LeoFilm> recentlyReleaseTV();

    @Select("select * from leo_film where leo_film_id = #{id}")
    LeoFilm findFilmById(Integer id);

    //评论--用户 多表联动
    @Select("SELECT * FROM leo_film_comment a LEFT JOIN leo_user b ON a.leo_film_comment_user_id=b.leo_user_id WHERE leo_film_comment_film_id = #{id}  order by leo_film_comment_agree desc limit 5")
    List<LeoFilmComment> findCommentHotFiveByFilmId(Integer id);

    @Select("select * from leo_film where leo_film_id in (SELECT leo_film_comment_film_id FROM leo_film_comment where leo_film_comment_user_id=#{id} and leo_film_comment_status='想看' order by leo_film_comment_date desc  )  limit 5")
    List<LeoFilm> findFiveWantSeeFilmByUserId(Integer id);

    @Select("select * from leo_film where leo_film_id in (SELECT leo_film_comment_film_id FROM leo_film_comment where leo_film_comment_user_id=#{id} and leo_film_comment_status='看过' order by leo_film_comment_date desc  )  limit 5")
    List<LeoFilm> findFiveSeenFilmByUserId(Integer id);

    //电影--评论  多表联动
    @Select("select * from leo_film a LEFT JOIN leo_film_comment b on b.leo_film_comment_film_id=a.leo_film_id  where b.leo_film_comment_user_id=#{leoUserId} and b.leo_film_comment_status=#{collectStatus} ORDER BY b.leo_film_comment_date desc")
    List<LeoFilm> findMyCommentByUserId(Integer leoUserId,String collectStatus);

    @Select("select * from leo_film  where leo_film_releasedate > DATE_SUB(curdate(),INTERVAL 30 day)  LIMIT 10")
    List<LeoFilm> findRecommendFilmList();

    @Update("update leo_film SET leo_film_name=#{leoFilmName}, leo_film_director=#{leoFilmDirector},leo_film_editor=#{leoFilmEditor},leo_film_actor=#{leoFilmActor},leo_film_country=#{leoFilmCountry},leo_film_language=#{leoFilmLanguage},leo_film_releasedate=#{leoFilmReleasedate},leo_film_length=#{leoFilmLength},leo_film_cover=#{leoFilmCover},leo_film_introduce=#{leoFilmIntroduce},leo_film_form=#{leoFilmForm},leo_film_type=#{leoFilmType},leo_film_admindate=#{leoFilmAdmindate}  WHERE leo_film_id = #{leoFilmId}")
    void updateFilmById(LeoFilm leoFilm);

    @Update("update leo_film set leo_film_click_num = leo_film_click_num + 1 where leo_film_id = #{id}")
    void updateClick(Integer id);

    @Select("select * from leo_film order by leo_film_click_num desc limit 10")
    List<LeoFilm> findClickRank();

    @Select("SELECT * FROM leo_film WHERE leo_film_id<250 ORDER BY leo_film_score desc ")
    List<LeoFilm> find250();

    @Insert("INSERT INTO leo_film( leo_film_name, leo_film_director, leo_film_editor, leo_film_actor, leo_film_country, leo_film_language, leo_film_releasedate, leo_film_length, leo_film_cover, leo_film_trailer, leo_film_introduce, leo_film_form, leo_film_type,leo_film_admindate) VALUES (#{leoFilmName}, #{leoFilmDirector}, #{leoFilmEditor}, #{leoFilmActor}, #{leoFilmCountry},  #{leoFilmLanguage},  #{leoFilmReleasedate}, #{leoFilmLength},  #{leoFilmCover},  #{leoFilmTrailer},  #{leoFilmIntroduce},  #{leoFilmForm},  #{leoFilmType},  #{leoFilmAdmindate});\n")
    void insertFilm(LeoFilm leoFilm);

    @Delete("delete from leo_film_comment where leo_film_comment_id=#{leoFilmCommentId}")
    void deleteMyCollectFilmByCommentId(Integer leoFilmCommentId);

    @Select("SELECT * FROM leo_film WHERE leo_film_releasedate > CURDATE() and leo_film_releasedate < date_sub(curdate(),interval -30 day)")
    List<LeoFilm> willRelease();

    @Update("UPDATE leo_film SET leo_film_score = (SELECT IFNULL(avg(leo_film_comment_score),0) FROM `leo_film_comment` WHERE leo_film.leo_film_id=leo_film_comment.leo_film_comment_film_id)")
    void intervalUpdateScore();
}
