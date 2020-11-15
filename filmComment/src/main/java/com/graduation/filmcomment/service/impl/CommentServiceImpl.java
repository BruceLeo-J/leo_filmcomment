package com.graduation.filmcomment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.filmcomment.entity.LeoAgreeStatus;
import com.graduation.filmcomment.entity.LeoFilm;
import com.graduation.filmcomment.entity.LeoFilmComment;
import com.graduation.filmcomment.entity.LeoUser;
import com.graduation.filmcomment.mapper.CommentMapper;
import com.graduation.filmcomment.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void insertComment(LeoFilmComment leoFilmComment) {
        commentMapper.insertComment(leoFilmComment);
    }

    @Override
    public PageInfo<LeoFilmComment> findCommentByFilmId(Integer id, Integer pageNum, Integer pageSize, String sort) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(LeoFilmComment.class);

        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("leoFilmCommentFilmId", id);

        if ("hot".equals(sort)) {
            example.setOrderByClause("leo_film_comment_agree desc");
        }
        if ("latest".equals(sort)) {
            example.setOrderByClause("leo_film_comment_date  desc");
        }

        List<LeoFilmComment> conditionList = commentMapper.selectByExample(example);
        List<LeoFilmComment> allList = commentMapper.commentSelectAll();

        for (LeoFilmComment allListL : allList) {
            for (LeoFilmComment conditionListL : conditionList) {
                if (conditionListL.getLeoFilmCommentId() == allListL.getLeoFilmCommentId()) {
                    BeanUtils.copyProperties(allListL, conditionListL);
                }
            }
        }
        PageInfo<LeoFilmComment> pageInfo = new PageInfo<>(conditionList);
        return pageInfo;
    }


    @Override
    public LeoFilm findFilmById(Integer id) {
        return commentMapper.findFilmById(id);
    }

    @Override
    public PageInfo<LeoFilmComment> CommentSelectAll(Integer pageIndex, Integer pageSize, LeoFilmComment leoFilmComment, Integer leoUserId) {

        Example example = new Example(LeoFilmComment.class);
        Example.Criteria criteria = example.createCriteria();
        if (leoUserId != null ) {
            criteria.andEqualTo("leoFilmCommentUserId",leoUserId);
        }
        List<LeoFilmComment> allList = commentMapper.commentSelectAll();
        PageHelper.startPage(pageIndex, pageSize);
        List<LeoFilmComment> conditionList = commentMapper.selectByExample(example);
        for (LeoFilmComment allListL : allList) {
            for (LeoFilmComment conditionListL : conditionList) {
                if (conditionListL.getLeoFilmCommentId() == allListL.getLeoFilmCommentId()) {
                    BeanUtils.copyProperties(allListL, conditionListL);
                }
            }
        }

        PageInfo<LeoFilmComment> pageInfo = new PageInfo<>(conditionList);
        return pageInfo;
    }

    @Override
    public void deleteCommentById(LeoFilmComment leoFilmComment) {
        commentMapper.deleteCommentById(leoFilmComment.getLeoFilmCommentId());
    }

    @Override
    public LeoFilmComment findCommentByCommentId(Integer leoFilmCommentId) {
        return commentMapper.findCommentByCommentId(leoFilmCommentId);
    }

    @Override
    public void updateAgreeByCommentId(Integer leoFilmCommentId, Integer result) {
        commentMapper.updateAgreeByCommentId(leoFilmCommentId, result);
    }

    @Override
    public LeoAgreeStatus checkAgreeStatus(Integer leoUserId, Integer leoFilmCommentId) {
        return commentMapper.checkAgreeStatus(leoUserId, leoFilmCommentId);
    }

    @Override
    public void updateAgreeStatus(Integer leoUserId, Integer leoFilmCommentId, boolean b) {
        commentMapper.updateAgreeStatus(leoUserId, leoFilmCommentId, b);
    }

    @Override
    public void insertUserAgreeStatus(Integer leoUserId, Integer leoFilmCommentId, boolean b) {
        commentMapper.insertUserAgreeStatus(leoUserId, leoFilmCommentId, b);
    }

    @Override
    public LeoFilmComment findCommentByFilmAndUserId(Integer leoFilmId, Integer leoUserId) {
        return commentMapper.findCommentByFilmAndUserId(leoFilmId,leoUserId);
    }
}
