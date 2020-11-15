package com.graduation.filmcomment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.filmcomment.entity.LeoFilm;
import com.graduation.filmcomment.entity.LeoFilmComment;
import com.graduation.filmcomment.mapper.FilmMapper;
import com.graduation.filmcomment.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.List;


@Service
public class FilmServiceImpl implements FilmService{


    @Autowired
    private FilmMapper filmMapper;

    public List<LeoFilm> recentlyRelease(){
        List<LeoFilm> filmList = filmMapper.recentlyRelease();
        return filmList;
    }

    @Override
    public List<LeoFilm> recentlyReleaseTV() {
        List<LeoFilm> TVList = filmMapper.recentlyReleaseTV();
        return TVList;
    }

    @Override
    public LeoFilm findFilmById(Integer id) {
        filmMapper.updateClick(id);
        return filmMapper.findFilmById(id);
    }

    @Override
    public List<LeoFilmComment> findCommentHotFiveByFilmId(Integer id) {
        return filmMapper.findCommentHotFiveByFilmId(id);
    }

    @Override
    public List<LeoFilm> findMyCommentByUserId(Integer leoUserId, String collectStatus) {
        return filmMapper.findMyCommentByUserId(leoUserId,collectStatus);
    }

    @Override
    public List<LeoFilm> findRecommendFilmList() {
        return filmMapper.findRecommendFilmList();
    }

    @Override
    public void deleteMyCollectFilmByCommentId(Integer leoFilmCommentId) {
        filmMapper.deleteMyCollectFilmByCommentId(leoFilmCommentId);
    }


//    -------------------------------Admin-------------------------

    @Override
    public PageInfo<LeoFilm> filmSelectAll(int pageNum, int pageSize,LeoFilm leoFilm)  {
        PageHelper.startPage(pageNum,pageSize);         //pageIndex：当前页数   pageSize：当前页需要显示的数量
        Example example = new Example(LeoFilm.class);
        Example.Criteria criteria = example.createCriteria();

        if(leoFilm.getLeoFilmAdmindate()!=null && !leoFilm.getLeoFilmAdmindate().equals(",")){

            String[] split = leoFilm.getLeoFilmAdmindate().split(",");

            if(leoFilm.getLeoFilmAdmindate().substring(0,1).equals(",")){
                criteria.andLessThanOrEqualTo("leoFilmAdmindate",split[1]);    //查询小于截止日期的所有电影
            }
            else if (leoFilm.getLeoFilmAdmindate().substring(leoFilm.getLeoFilmAdmindate().length()-1).equals(",")){  //只有截止日期
                criteria.andGreaterThanOrEqualTo("leoFilmAdmindate",split[0]); //查询大于起始日期的所有电影
            }
            else {
                criteria.andBetween("leoFilmAdmindate",split[0],split[1]);
            }
        }
        if(leoFilm.getLeoFilmForm()!=null){
            criteria.andLike("leoFilmForm","%"+leoFilm.getLeoFilmForm()+"%");
        }
        if(leoFilm.getLeoFilmType()!=null){
            criteria.andLike("leoFilmType","%"+leoFilm.getLeoFilmType()+"%");
        }
        if(leoFilm.getLeoFilmName()!=null){
            criteria.andLike("leoFilmName","%"+leoFilm.getLeoFilmName()+"%");
        }
        List<LeoFilm> leoFilmList = filmMapper.selectByExample(example);
        PageInfo<LeoFilm> pageInfo= new PageInfo<>(leoFilmList);
        return pageInfo;
    }

    @Override
    public void addFilm(LeoFilm leoFilm) {
        filmMapper.insertFilm(leoFilm);
    }

    @Override
    public void deleteFilmById(int leoFilmId) {
        filmMapper.deleteByPrimaryKey(leoFilmId);
    }

    @Override
    public void updateFilmById(LeoFilm leoFilmId) {
        filmMapper.updateFilmById(leoFilmId);
    }

    @Override
    public PageInfo<LeoFilm> find250(Integer pageNum,Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        List<LeoFilm> leoFilmList = filmMapper.find250();
        PageInfo<LeoFilm> pageInfo = new PageInfo(leoFilmList);
        return pageInfo;
    }

    @Override
    public List<LeoFilm> findFilmByCondition(String searchKey) {
        Example example = new Example(LeoFilm.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchKey!="空条件"){
        criteria.andLike("leoFilmName",'%'+searchKey+'%');
        }
        return filmMapper.selectByExample(example);
    }

    @Override
    public List<LeoFilm> findClickRank() {
        return filmMapper.findClickRank();
    }

    @Override
    public PageInfo<LeoFilm> pickFilm(Integer pageNum, Integer pageSize, LeoFilm leoFilm, String sort) {


        Example example = new Example(LeoFilm.class);
        Example.Criteria criteria = example.createCriteria();


            if(leoFilm.getLeoFilmType()!=null && !leoFilm.getLeoFilmType().equals("") ){
                criteria.andLike("leoFilmType",'%'+leoFilm.getLeoFilmType()+'%');
            }
            if(leoFilm.getLeoFilmCountry()!=null && !leoFilm.getLeoFilmCountry().equals("")){
                criteria.andLike("leoFilmCountry",'%'+leoFilm.getLeoFilmCountry()+'%');
            }
            if(leoFilm.getLeoFilmLanguage()!=null && !leoFilm.getLeoFilmLanguage().equals("")){
                criteria.andLike("leoFilmLanguage",'%'+leoFilm.getLeoFilmLanguage()+'%');
            }
            if(leoFilm.getLeoFilmForm()!=null && !leoFilm.getLeoFilmForm().equals("")){
                criteria.andLike("leoFilmForm",'%'+leoFilm.getLeoFilmForm()+'%');
            }

            if("hot".equals(sort)){
                example.setOrderByClause("leo_film_click_num desc");
            }
            if("time".equals(sort)){
                example.setOrderByClause("leo_film_releasedate desc");
            }
            if("comment".equals(sort)){
                example.setOrderByClause("leo_film_score desc");
            }


        PageHelper.startPage(pageNum,pageSize);
        List<LeoFilm> leoFilmList = filmMapper.selectByExample(example);
        PageInfo<LeoFilm> pageInfo = new PageInfo(leoFilmList);
        return pageInfo;
    }

    @Override
    public List<LeoFilm> willRelease() {
        return filmMapper.willRelease();
    }



}
