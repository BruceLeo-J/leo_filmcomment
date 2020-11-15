package com.graduation.filmcomment.service;

import com.github.pagehelper.PageInfo;
import com.graduation.filmcomment.entity.LeoAgreeStatus;
import com.graduation.filmcomment.entity.LeoFilm;
import com.graduation.filmcomment.entity.LeoFilmComment;

import java.util.List;

public interface CommentService {
    void insertComment(LeoFilmComment leoFilmComment);

    PageInfo<LeoFilmComment> findCommentByFilmId(Integer id, Integer pageNum, Integer pageSize,String sort);

    LeoFilm findFilmById(Integer id);

    PageInfo<LeoFilmComment> CommentSelectAll(Integer pageIndex, Integer pageSize, LeoFilmComment leoFilmComment,Integer leoUserId);

    void deleteCommentById(LeoFilmComment leoFilmComment);

    LeoFilmComment findCommentByCommentId(Integer leoFilmCommentId);

    void updateAgreeByCommentId(Integer leoFilmCommentId, Integer result);

    LeoAgreeStatus checkAgreeStatus(Integer leoUserId, Integer leoFilmCommentId);

    void updateAgreeStatus(Integer leoUserId, Integer leoFilmCommentId, boolean b);

    void insertUserAgreeStatus(Integer leoUserId, Integer leoFilmCommentId, boolean b);

    LeoFilmComment findCommentByFilmAndUserId(Integer leoFilmId, Integer leoUserId);
}
