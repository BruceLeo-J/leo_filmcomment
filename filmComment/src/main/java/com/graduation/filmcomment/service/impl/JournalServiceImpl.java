package com.graduation.filmcomment.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.filmcomment.entity.LeoJournal;
import com.graduation.filmcomment.entity.LeoJournalComment;
import com.graduation.filmcomment.entity.LeoLikeStatus;
import com.graduation.filmcomment.mapper.JournalMapper;
import com.graduation.filmcomment.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalMapper journalMapper;

    @Override
    public void addJournal(LeoJournal leoJournal) {
        journalMapper.addJournal(leoJournal);
    }

    @Override
    public LeoJournal selectJournalById(Integer journalId) {
        return journalMapper.selectJournalById(journalId);
    }

    @Override
    public PageInfo<LeoJournal> selectAll(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<LeoJournal> leoJournalList = journalMapper.selectAll();
        PageInfo<LeoJournal> pageInfo = new PageInfo<>(leoJournalList);
        return pageInfo;
    }

    @Override
    public LeoLikeStatus checkUserExist(Integer leoUserId, Integer leoJournalId) {
        return journalMapper.checkUserExist(leoUserId,leoJournalId);
    }

    @Override
    public void insertStatus(Integer leoUserId, Integer journalId, boolean b) {
        journalMapper.insertStatus(leoUserId,journalId,b);
    }

    @Override
    public void updateJournalLikeNum(Integer journalId, int i) {
        journalMapper.updateJournalLikeNum(journalId,i);
    }

    @Override
    public void updateStatus(Integer leoUserId, Integer leoJournalId, boolean b) {
        journalMapper.updateStatus(leoUserId,leoJournalId,b);
    }

    @Override
    public void addComment(LeoJournalComment leoJournalComment) {
        journalMapper.addComment(leoJournalComment);
    }

    @Override
    public List<LeoJournalComment> selectCommentByJournalId(Integer journalId) {
        return journalMapper.selectCommentByJournalId(journalId);
    }

    @Override
    public List<LeoJournalComment> selectSecondCommentByJournalId(Integer journalId) {
        return journalMapper.selectSecondCommentByJournalId(journalId);
    }

    @Override
    public List<LeoJournal> recommendJournalList() {
        return journalMapper.recommendJournalList();
    }

}
