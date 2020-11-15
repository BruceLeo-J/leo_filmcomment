package com.graduation.filmcomment.controller;

import com.github.pagehelper.PageInfo;
import com.graduation.filmcomment.entity.LeoJournal;
import com.graduation.filmcomment.entity.LeoJournalComment;
import com.graduation.filmcomment.entity.LeoLikeStatus;
import com.graduation.filmcomment.entity.LeoUser;
import com.graduation.filmcomment.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("journal")
public class JournalController {

   @Autowired
   private JournalService journalService;

    @RequestMapping("journalList")
    private String toJournal(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                                        Model model){
        PageInfo<LeoJournal> pageInfo = journalService.selectAll(pageNum,pageSize);
        for (LeoJournal leoJournal : pageInfo.getList()) {
            leoJournal.setLeoJournalContent(leoJournal.getLeoJournalContent()
                    .replaceAll("<p>","").replaceAll("</p>","")
                    .replaceAll("<b>","").replaceAll("</b>",""));
            if(leoJournal.getLeoJournalContent().length()>106){
               leoJournal.setLeoJournalContent(leoJournal.getLeoJournalContent().substring(0,106)+"...");
            }
        }
        model.addAttribute("pageInfo",pageInfo);
        return "journalList";
    }

    @RequestMapping("journalDetail")
    private String journalDetail(Integer journalId, Model model,String scrool){
        LeoJournal leoJournal = journalService.selectJournalById(journalId);
        List<LeoJournalComment> leoJournalCommentList = journalService.selectCommentByJournalId(journalId);
        List<LeoJournalComment> secondCommentList = journalService.selectSecondCommentByJournalId(journalId);
        model.addAttribute("leoJournal",leoJournal);
        model.addAttribute("leoJournalCommentList",leoJournalCommentList);
        model.addAttribute("secondCommentList",secondCommentList);
        model.addAttribute("scrool",scrool);
        return "journalDetail";
    }

    @RequestMapping("iLike")
    @ResponseBody
    private Map iLike(Integer journalId, HttpServletRequest request){
        LeoUser leoUser = (LeoUser) request.getSession().getAttribute("user");
        HashMap<String,Object> map = new HashMap();
        if (leoUser==null){
            map.put("code",-1);
        }else {
            //查询状态表，是否有该用户
            LeoLikeStatus leoLikeStatus = journalService.checkUserExist(leoUser.getLeoUserId(),journalId);
            if(leoLikeStatus==null){ //没有该用户，添加状态，并更新喜欢数+1
                journalService.insertStatus(leoUser.getLeoUserId(),journalId,true);
                journalService.updateJournalLikeNum(journalId,1);
            }else {  //存在该用户，判断状态
                if (leoLikeStatus.getLeoLikeStatus()==true){ //改变状态，并使喜欢数-1
                    journalService.updateStatus(leoLikeStatus.getLeoUserId(),leoLikeStatus.getLeoJournalId(),false);
                    journalService.updateJournalLikeNum(journalId,-1);
                    map.put("likeStatus",false); //喜欢
                }else {//改变状态，并使喜欢数+1
                    journalService.updateStatus(leoLikeStatus.getLeoUserId(),leoLikeStatus.getLeoJournalId(),true);
                    journalService.updateJournalLikeNum(journalId,+1);
                    map.put("likeStatus",true); //已喜欢
                }
                LeoJournal leoJournal = journalService.selectJournalById(journalId);
                map.put("likeCount",leoJournal.getLeoJournalLike());
            }
        }
        return map;
    }

    @RequestMapping("checkLikeStatus")
    @ResponseBody
    private Map checkLikeStatus(Integer journalId,HttpServletRequest request){
        LeoUser leoUser = (LeoUser) request.getSession().getAttribute("user");
        LeoLikeStatus leoLikeStatus = journalService.checkUserExist(leoUser.getLeoUserId(), journalId);
        HashMap<String,Object> map = new HashMap();
        if (leoLikeStatus!=null){
            map.put("likeStatus",leoLikeStatus.getLeoLikeStatus());
        }else {
            map.put("likeStatus",false);
        }
        return map;
    }

    @RequestMapping("addComment")
    private String addComment(LeoJournalComment leoJournalComment,Model model,HttpServletRequest request){
        if (leoJournalComment.getLeoJournalCommentParentId()==null){
            leoJournalComment.setLeoJournalCommentParentId(0);
        }
        journalService.addComment(leoJournalComment);
        LeoJournal leoJournal = journalService.selectJournalById(leoJournalComment.getLeoJournalId());
        model.addAttribute("leoJournal",leoJournal);
        return "redirect:/journal/journalDetail?scrool=5000&journalId="+leoJournalComment.getLeoJournalId();
    }

//--------------------------admin------------------------

    @RequestMapping("adminJournalEdit")
    private String adminJournalEdit(){
        return "/admin/admin-journal-edit";
    }

    @RequestMapping("uploadFile")
    @ResponseBody
    private HashMap<String,Object> uploadFile(MultipartFile file) throws IOException {
        HashMap<String, Object> map = new HashMap();
        if (file != null && !file.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            file.transferTo(new File("D:\\IdeaProjects\\filmComment\\src\\main\\resources\\static\\img\\" + uuid + file.getOriginalFilename()));
            map.put("code",0);
            map.put("msg", "");
            HashMap<String,Object> map1 = new HashMap<>();
            map.put("data", map1);
            map1.put("src","/img/"+uuid+file.getOriginalFilename());
            map1.put("title","");
            return map;
        } else {
            map.put("code", -1);
            return map;
        }
    }

    @RequestMapping("submitJournalForm")
    private String submitJournalForm(LeoJournal leoJournal){
        journalService.addJournal(leoJournal);
        return "/admin/admin-journal-edit";
    }

}
