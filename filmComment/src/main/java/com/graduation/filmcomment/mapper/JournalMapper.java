package com.graduation.filmcomment.mapper;

import com.graduation.filmcomment.entity.LeoJournal;
import com.graduation.filmcomment.entity.LeoJournalComment;
import com.graduation.filmcomment.entity.LeoLikeStatus;
import com.graduation.filmcomment.entity.LeoUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface JournalMapper extends tk.mybatis.mapper.common.Mapper<LeoJournal> {

    @Insert("insert into leo_journal(leo_journal_title,leo_journal_content,leo_journal_like,leo_journal_date)  values( #{leoJournalTitle} , #{leoJournalContent} , 0 , curdate() )")
    void addJournal(LeoJournal leoJournal);

    @Select("select * from leo_journal where leo_journal_id = #{id} ")
    LeoJournal selectJournalById(Integer id);

    @Select("select * from leo_like_status where leo_user_id = #{leoUserId} and leo_journal_id = #{leoJournalId}")
    LeoLikeStatus checkUserExist(Integer leoUserId,Integer leoJournalId);

    @Select("select * from leo_journal order by leo_journal_like desc limit 5")
    List<LeoJournal> recommendJournalList();

    @Insert("insert into leo_like_status values(#{leoUserId},#{journalId},#{b})")
    void insertStatus(Integer leoUserId, Integer journalId, boolean b);

    @Update("update leo_journal set leo_journal_like = leo_journal_like + #{i} where leo_journal_id = #{journalId}")
    void updateJournalLikeNum(Integer journalId, int i);

    @Update("update leo_like_status set leo_like_status = #{b} where leo_user_id = #{leoUserId} and leo_journal_id = #{leoJournalId}")
    void updateStatus(Integer leoUserId, Integer leoJournalId, boolean b);

    @Insert("insert into leo_journal_comment(leo_journal_comment_parent_id,leo_journal_comment_level,leo_journal_id,leo_user_id,leo_journal_comment_content) values(#{leoJournalCommentParentId},#{leoJournalCommentLevel},#{leoJournalId},#{leoUserId},#{leoJournalCommentContent})")
    void addComment(LeoJournalComment leoJournalComment);

    @Select("select * from leo_journal_comment where leo_journal_id = #{journalId} and leo_journal_comment_level = 1 ")
    @Results({
    @Result(id = true,column = "leo_journal_comment_id",property = "leoJournalCommentId"),
    @Result(column = "leo_journal_comment_parent_id",property = "leoJournalCommentParentId"),
    @Result(column = "leo_journal_id",property = "leoJournalId"),
    @Result(column = "leo_journal_comment_content",property = "leoJournalCommentContent"),
    @Result(column = "leo_journal_comment_date",property = "leoJournalCommentDate"),
    @Result(column = "leo_user_id",property = "leoUser",javaType = LeoUser.class,one = @One(select = "com.graduation.filmcomment.mapper.UserMapper.findUserById"))})
    List<LeoJournalComment> selectCommentByJournalId(Integer journalId);

    @Select("select * from leo_journal_comment where leo_journal_id = #{journalId} and leo_journal_comment_level = 2 ")
    @Results({
            @Result(id = true,column = "leo_journal_comment_id",property = "leoJournalCommentId"),
            @Result(column = "leo_journal_comment_parent_id",property = "leoJournalCommentParentId"),
            @Result(column = "leo_journal_id",property = "leoJournalId"),
            @Result(column = "leo_journal_comment_content",property = "leoJournalCommentContent"),
            @Result(column = "leo_journal_comment_date",property = "leoJournalCommentDate"),
            @Result(column = "leo_user_id",property = "leoUser",javaType = LeoUser.class,one = @One(select = "com.graduation.filmcomment.mapper.UserMapper.findUserById"))})
    List<LeoJournalComment> selectSecondCommentByJournalId(Integer journalId);


}
