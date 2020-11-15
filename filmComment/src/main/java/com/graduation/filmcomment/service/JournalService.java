package com.graduation.filmcomment.service;

import com.github.pagehelper.PageInfo;
import com.graduation.filmcomment.entity.LeoJournal;
import com.graduation.filmcomment.entity.LeoJournalComment;
import com.graduation.filmcomment.entity.LeoLikeStatus;

import java.util.List;

public interface JournalService {
    void addJournal(LeoJournal leoJournal);

    LeoJournal selectJournalById(Integer journalId);

    PageInfo<LeoJournal> selectAll(Integer pageNum, Integer pageSize);

    LeoLikeStatus checkUserExist(Integer leoUserId, Integer leoJournalId);

    void insertStatus(Integer leoUserId, Integer journalId, boolean b);

    void updateJournalLikeNum(Integer journalId, int i);

    void updateStatus(Integer leoUserId, Integer leoJournalId, boolean b);

    void addComment(LeoJournalComment leoJournalComment);

    List<LeoJournalComment> selectCommentByJournalId(Integer journalId);

    List<LeoJournalComment> selectSecondCommentByJournalId(Integer journalId);

    List<LeoJournal> recommendJournalList();
}
