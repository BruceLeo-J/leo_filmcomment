package com.graduation.filmcomment.controller;

import com.github.pagehelper.PageInfo;
import com.graduation.filmcomment.entity.*;
import com.graduation.filmcomment.service.CommentService;
import com.graduation.filmcomment.service.TypeService;
import com.graduation.filmcomment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    /**
     * 判断用户禁用/开启状态，判断用户评论是否已经存在。
     * @param leoFilmId
     * @param request
     * @return
     */
    @RequestMapping("/checkCommentExistAndUserStatus")
    @ResponseBody
    public Map checkCommentExist(Integer leoFilmId,HttpServletRequest request){
        LeoUser leoUser = (LeoUser) request.getSession().getAttribute("user");
        HashMap<String,Object> map = new HashMap<>();
        LeoUser userById = userService.findUserById(leoUser.getLeoUserId());
        LeoFilmComment leoFilmComment = commentService.findCommentByFilmAndUserId(leoFilmId,leoUser.getLeoUserId());
        if(leoFilmComment!=null){
            map.put("code",-1);
        }
        if(userById.getLeoUserStatus()==false){
            map.put("code",-2);
        }
        return  map;
    }

    @RequestMapping("/insertComment")
    public String filmComment(LeoFilmComment leoFilmComment){
        commentService.insertComment(leoFilmComment);
        return "redirect:/intro/filmDetail?id="+leoFilmComment.getLeoFilm().getLeoFilmId();
    }

    @GetMapping("/allComment")
    public String allComment(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                         Integer leoFilmId,Model model,String sort){
        PageInfo<LeoFilmComment> pageInfo = commentService.findCommentByFilmId(leoFilmId,pageNum,pageSize,sort);
        LeoFilm leoFilm = commentService.findFilmById(leoFilmId);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("leoFilm",leoFilm);
        model.addAttribute("backSort",sort);
        return "allComment";
    }

    /**
     * 给电影的评论进行点赞（有用）
     * @param leoFilmComment
     * @param request
     * @return
     */
    @RequestMapping("/agreeComment")
    @ResponseBody
    public Map<String,Object> agreeComment(LeoFilmComment leoFilmComment, HttpServletRequest request){

        Map<String,Object> map = new HashMap<>();

        LeoUser leoUser = (LeoUser) request.getSession().getAttribute("user");
        if(leoUser==null){
            map.put("code",-1);
        }else {
          LeoAgreeStatus leoAgreeStatus = commentService.checkAgreeStatus(leoUser.getLeoUserId(),leoFilmComment.getLeoFilmCommentId());
            if(leoAgreeStatus==null){
                //没有点过赞
                commentService.updateAgreeByCommentId(leoFilmComment.getLeoFilmCommentId(),1);
                commentService.insertUserAgreeStatus(leoUser.getLeoUserId(),leoFilmComment.getLeoFilmCommentId(),true);
            }else {
                //点过赞了，判断状态
                if(leoAgreeStatus.getLeoAgreeStatus()==true){
                    //赞过了，取消赞。
                    commentService.updateAgreeByCommentId(leoFilmComment.getLeoFilmCommentId(),-1);
                    commentService.updateAgreeStatus(leoUser.getLeoUserId(),leoFilmComment.getLeoFilmCommentId(),false);
                }else {
                    //取消赞了，重新点赞
                    commentService.updateAgreeByCommentId(leoFilmComment.getLeoFilmCommentId(),1);
                    commentService.updateAgreeStatus(leoUser.getLeoUserId(),leoFilmComment.getLeoFilmCommentId(),true);
                }
            }
                LeoFilmComment changedAgreeComment = commentService.findCommentByCommentId(leoFilmComment.getLeoFilmCommentId());
                map.put("agreeCount",changedAgreeComment.getLeoFilmCommentAgree());
                map.put("commentId",changedAgreeComment.getLeoFilmCommentId());

        }
        return map;
    }



//    ------------------------Admin-------------------------

    @RequestMapping("/adminCommentList")
    private String adminCommentList(@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                 LeoFilmComment leoFilmComment,Integer leoUserId,Model model) {
        PageInfo<LeoFilmComment> pageInfo = commentService.CommentSelectAll(pageIndex,pageSize,leoFilmComment,leoUserId);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("backUserId",leoUserId);
        return "admin/admin-comment-list";
    }

    @RequestMapping("/deleteCommentById")
    private String deleteCommentById(Integer leoFilmCommentId) {
        LeoFilmComment leoFilmComment = commentService.findCommentByCommentId(leoFilmCommentId);
        commentService.deleteCommentById(leoFilmComment);
        return "redirect:/comment/adminCommentList";
    }


}
