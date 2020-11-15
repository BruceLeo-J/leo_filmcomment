package com.graduation.filmcomment.service;


import com.github.pagehelper.PageInfo;
import com.graduation.filmcomment.entity.LeoFilm;
import com.graduation.filmcomment.entity.LeoFilmComment;

import java.util.List;

public interface FilmService {

    List<LeoFilm> recentlyRelease();

    List<LeoFilm> recentlyReleaseTV();

    LeoFilm findFilmById(Integer id);

    List<LeoFilmComment> findCommentHotFiveByFilmId(Integer id);

    List<LeoFilm> findMyCommentByUserId(Integer leoUserId, String collectStatus);

    List<LeoFilm> findRecommendFilmList();

//    -------------------------------Admin-------------------------

    PageInfo<LeoFilm> filmSelectAll(int pageNum, int pageSize,LeoFilm leoFilmCondition) ;

    void addFilm(LeoFilm leoFilm);

    void deleteFilmById(int leoFilmId);

    void updateFilmById(LeoFilm leoFilmId);

    PageInfo<LeoFilm> find250(Integer pageNum,Integer pageSize);

    List<LeoFilm> findFilmByCondition(String searchKey);

    List<LeoFilm> findClickRank();

    PageInfo<LeoFilm> pickFilm(Integer pageNum, Integer pageSize, LeoFilm leoFilm, String sort);

    List<LeoFilm> willRelease();

    void deleteMyCollectFilmByCommentId(Integer leoFilmCommentId);

}
