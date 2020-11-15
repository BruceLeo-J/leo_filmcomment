package com.graduation.filmcomment.controller;

import com.github.pagehelper.PageInfo;
import com.graduation.filmcomment.entity.*;
import com.graduation.filmcomment.service.FilmService;
import com.graduation.filmcomment.service.JournalService;
import com.graduation.filmcomment.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/intro")
public class FilmController {
    @Autowired
    private FilmService filmService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private JournalService journalService;


    @RequestMapping("/searchFilm")
    private String searchFilm(@RequestParam(value = "searchKey",defaultValue = "空条件") String searchKey,Model model)  {
        List<LeoFilm> leoFilmList = filmService.findFilmByCondition(searchKey);
        model.addAttribute("leoFilmList",leoFilmList);
        if(leoFilmList.size()==0){
            model.addAttribute("nullMsg","没有找到关于 “"+searchKey+"” 的影视信息，换个搜索词试试吧！");
        }
        return "searchPage";
    }



    @RequestMapping("/main")
    public String main(Model model){
        List<LeoFilm> filmList = filmService.recentlyRelease();
        List<LeoFilm> TVList = filmService.recentlyReleaseTV();
        List<LeoFilm> recommendFilmList = filmService.findRecommendFilmList();
        List<LeoFilm> clickRankList =filmService.findClickRank();
        List<LeoJournal> leoJournalList =  journalService.recommendJournalList();
        model.addAttribute("filmList",filmList);
        model.addAttribute("TVList",TVList);
        model.addAttribute("recommendFilmList",recommendFilmList);
        model.addAttribute("clickRankList",clickRankList);
        model.addAttribute("leoJournalList",leoJournalList);
        return "main";
    }

    @GetMapping("/filmDetail")
    public String filmDetail(Integer id,Model model,HttpServletRequest request){
        LeoFilm leoFilm = filmService.findFilmById(id);
        List<LeoFilmComment> leoFilmCommentHotfive = filmService.findCommentHotFiveByFilmId(id);
        model.addAttribute("leoFilm",leoFilm);
        request.getSession().setAttribute("leoFilmSession",leoFilm);
        model.addAttribute("leoFilmCommentHotfive",leoFilmCommentHotfive);
        return "filmDetail";
    }

    @RequestMapping("/filmTrailer")
    public String filmTrailer(LeoFilm leoFilm,Model model){
        LeoFilm leoFilmModel = filmService.findFilmById(leoFilm.getLeoFilmId());
        model.addAttribute("leoFilm",leoFilmModel);
        return "trailer";
    }

    @RequestMapping("/myCollectFilm")
    public String myCollectFilm(String collectStatus,Model model,HttpServletRequest request){
        LeoUser leoUser = (LeoUser) request.getSession().getAttribute("user");
        if(leoUser!=null){
            List<LeoFilm> leoFilmList = filmService.findMyCommentByUserId(leoUser.getLeoUserId(),collectStatus);
            model.addAttribute("leoFilmList",leoFilmList);
            model.addAttribute("leoUser",leoUser);
        }
        model.addAttribute("backStatus",collectStatus);
        return "myCollectFilm";
    }

    @RequestMapping("/deleteMyCollectFilmByFilmId")
    private String deleteMyCollectFilmByFilmId(Integer leoFilmCommentId, String leoFilmCommentStatus, RedirectAttributes attr)  {
        filmService.deleteMyCollectFilmByCommentId(leoFilmCommentId);
        attr.addAttribute("collectStatus",leoFilmCommentStatus);
        return "redirect:/intro/myCollectFilm";
    }


    @GetMapping("/willRelease")
    public String willRelease(Model model){
        List<LeoFilm> willReleaseList = filmService.willRelease();
        model.addAttribute("willReleaseList",willReleaseList);
        return "willRelease";
    }


    @GetMapping("/top250")
    public String find250(@RequestParam (value = "pageNum",defaultValue = "1" ) Integer pageNum,
                       @RequestParam  (value = "pageSize",defaultValue = "10" )Integer pageSize,
                                                                   Model model)
    {
        PageInfo<LeoFilm> pageInfo = filmService.find250(pageNum,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "top250";
    }

    @GetMapping("/pickFilm")
    public String pickFilm(@RequestParam (value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam (value = "pageSize",defaultValue = "10")Integer pageSize,
                            Model model,LeoFilm leoFilm,String sort){
        PageInfo<LeoFilm> pageInfo = filmService.pickFilm(pageNum,  pageSize, leoFilm, sort);
        List<LeoFilmType> leoFilmTypeList = typeService.findFilmType();
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("sort",sort);
        model.addAttribute("backType",leoFilm.getLeoFilmType());
        model.addAttribute("leoFilmTypeList",leoFilmTypeList);
        model.addAttribute("backForm",leoFilm.getLeoFilmForm());

        return "pickFilm";
    }

    @GetMapping("/allSort")
    public String allSort(@RequestParam (value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam (value = "pageSize",defaultValue = "10")Integer pageSize,
                           Model model,LeoFilm leoFilm,String sort){

        PageInfo<LeoFilm> pageInfo = filmService.pickFilm(pageNum,  pageSize, leoFilm, sort);
        List<LeoFilmType> leoFilmTypeList = typeService.findFilmType();
        List<LeoFilmForm> leoFilmFormList = typeService.findFilmForm();
        List<LeoFilmCountry> leoFilmCountryList = typeService.findFilmCountry();
        List<LeoFilmLanguage> leoFilmLanguageList = typeService.findFilmLanguage();

        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("sort",sort);
        model.addAttribute("backType",leoFilm.getLeoFilmType());
        model.addAttribute("backCountry",leoFilm.getLeoFilmCountry());
        model.addAttribute("backLanguage",leoFilm.getLeoFilmLanguage());
        model.addAttribute("backForm",leoFilm.getLeoFilmForm());

        model.addAttribute("leoFilmTypeList",leoFilmTypeList);
        model.addAttribute("leoFilmFormList",leoFilmFormList);
        model.addAttribute("leoFilmCountryList",leoFilmCountryList);
        model.addAttribute("leoFilmLanguageList",leoFilmLanguageList);

        return "allSort";
    }

//    -------------------------------Admin-------------------------




//    影视管理

    @RequestMapping("/adminFilmList")
    private String adminFilmList(@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                 LeoFilm leoFilm,Model model) {

        List<LeoFilmType> leoFilmTypeList = typeService.findFilmType();//查询电影类型
        List<LeoFilmForm> leoFilmFormList = typeService.findFilmForm();//查询电影形式
        PageInfo<LeoFilm> leoFilmList = filmService.filmSelectAll(pageIndex,pageSize,leoFilm);
        model.addAttribute("leoFilmTypeList",leoFilmTypeList);
        model.addAttribute("leoFilmFormList",leoFilmFormList);
        model.addAttribute("leoFilmList",leoFilmList);
        if(leoFilm.getLeoFilmAdmindate()!=null && leoFilm.getLeoFilmAdmindate().length()>11){
            model.addAttribute("backStartDate",leoFilm.getLeoFilmAdmindate().split(",")[0]);
            model.addAttribute("backEndDate", leoFilm.getLeoFilmAdmindate().split(",")[1]);
        }else {
            if (leoFilm.getLeoFilmAdmindate()!=null && !leoFilm.getLeoFilmAdmindate().equals(",")&&leoFilm.getLeoFilmAdmindate().substring(0, 1).equals(",")) {
                model.addAttribute("backEndDate", leoFilm.getLeoFilmAdmindate().split(",")[1]);
            } else {
                model.addAttribute("backEndDate", "");
            }
            if (leoFilm.getLeoFilmAdmindate()!=null && !leoFilm.getLeoFilmAdmindate().equals(",")&&leoFilm.getLeoFilmAdmindate().substring(leoFilm.getLeoFilmAdmindate().length()-1).equals(",")){
                model.addAttribute("backStartDate",leoFilm.getLeoFilmAdmindate().split(",")[0]);
            }else {
                model.addAttribute("backStartDate","");
            }
        }
        model.addAttribute("backFilmForm",leoFilm.getLeoFilmForm());
        model.addAttribute("backFilmType",leoFilm.getLeoFilmType());
        model.addAttribute("backFilmName",leoFilm.getLeoFilmName());
        return "admin/admin-film-list";
    }


    @RequestMapping("/toAdminFilmAdd")
    private String toAdminFilmAdd(Model model){
        List<LeoFilmType> leoFilmTypeList = typeService.findFilmType();
        List<LeoFilmForm> leoFilmFormList = typeService.findFilmForm();
        model.addAttribute("leoFilmTypeList",leoFilmTypeList);
        model.addAttribute("leoFilmFormList",leoFilmFormList);
        return "admin/admin-film-add";
    }

    @RequestMapping("/findFilmDirectorAndEditorAndActor")
    @ResponseBody
    private List<JSONType> findFilmDirectorAndEditorAndActor(LeoFilm leoFilm){
        LeoFilm filmById = filmService.findFilmById(leoFilm.getLeoFilmId());
        List<JSONType> jsonTypeList = new ArrayList<>();
        String[] splitDirectorArr = filmById.getLeoFilmDirector().split(",");
        for (String splitDirector : splitDirectorArr) {
            JSONType jsonType = new JSONType();
            jsonType.setName(splitDirector);
            jsonType.setValue(splitDirector);
            jsonTypeList.add(jsonType);
        }
        String[] splitEditorArr = filmById.getLeoFilmEditor().split(",");
        for (String splitEditor : splitEditorArr) {
            JSONType jsonType = new JSONType();
            jsonType.setName(splitEditor);
            jsonType.setValue(splitEditor);
            jsonTypeList.add(jsonType);
        }
        String[] splitActorArr = filmById.getLeoFilmActor().split(",");
        for (String splitActor : splitActorArr) {
            JSONType jsonType = new JSONType();
            jsonType.setName(splitActor);
            jsonType.setValue(splitActor);
            jsonTypeList.add(jsonType);
        }
        return jsonTypeList;
    }
    @RequestMapping("/findFilmCountry")
    @ResponseBody
    private List<JSONType> findFilmCountry(){
        List<LeoFilmCountry> leoFilmCountryList = typeService.findFilmCountry();
        List<JSONType> jsonTypeList = new ArrayList<>();
        for (LeoFilmCountry leoFilmCountry : leoFilmCountryList) {
            JSONType jsonType = new JSONType();
            jsonType.setName(leoFilmCountry.getLeoFilmCountryName());
            jsonType.setValue(leoFilmCountry.getLeoFilmCountryName());
            jsonTypeList.add(jsonType);
        }
        return jsonTypeList;
    }

    @RequestMapping("/findFilmLanguage")
    @ResponseBody
    private List<JSONType> findFilmLanguage(){
        List<LeoFilmLanguage> LeoFilmLanguageList = typeService.findFilmLanguage();
        List<JSONType> jsonTypeList = new ArrayList<>();
        for (LeoFilmLanguage leoFilmLanguage : LeoFilmLanguageList) {
            JSONType jsonType = new JSONType();
            jsonType.setName(leoFilmLanguage.getLeoFilmLanguageName());
            jsonType.setValue(leoFilmLanguage.getLeoFilmLanguageName());
            jsonTypeList.add(jsonType);
        }
        return jsonTypeList;
    }

    @RequestMapping("/findFilmType")
    @ResponseBody
    private List<JSONType> findFilmType(){
        List<LeoFilmType> leoFilmTypeList = typeService.findFilmType();
        List<JSONType> jsonTypeList = new ArrayList<>();
        for (LeoFilmType leoFilmType : leoFilmTypeList) {
            JSONType jsonType = new JSONType();
            jsonType.setName(leoFilmType.getLeoFilmTypeName());
            jsonType.setValue(leoFilmType.getLeoFilmTypeName());
            jsonTypeList.add(jsonType);
        }
        return jsonTypeList;
    }

    @RequestMapping("/uploadFilm")
    @ResponseBody
    private HashMap<String,Object> uploadFilm(MultipartFile file) throws IOException{
        HashMap<String,Object> map = new HashMap();
        if (file != null && !file.isEmpty()) {
            file.transferTo(new File("D:\\IdeaProjects\\filmComment\\src\\main\\resources\\static\\img\\" +file.getOriginalFilename()));
            map.put("code", 0);
            map.put("imgFileName", file.getOriginalFilename());
            return map;
        }else {
            map.put("code", -1);
            map.put("msg", "文件上传出错，请重新上传！");
            return map;
        }
    }

    @RequestMapping("/uploadTrailer")
    @ResponseBody
    private HashMap<String,Object> uploadTrailer(MultipartFile file) throws IOException {
        HashMap<String,Object> map = new HashMap();
        if (file != null && !file.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            file.transferTo(new File("D:\\IdeaProjects\\filmComment\\src\\main\\resources\\static\\vedio\\" +uuid+file.getOriginalFilename()));
            map.put("code", 0);
            map.put("vedioFileName", "/vedio/"+uuid+file.getOriginalFilename());
        }
            return map;
    }

    @RequestMapping("/submitFilmForm")
    private String submitFilmForm(LeoFilm leoFilm)  {
        filmService.addFilm(leoFilm);
        return "redirect:/admin/index";
    }

    @PostMapping("/deleteFilmById")
    private String deleteFilmById(Integer leoFilmId)  {
       filmService.deleteFilmById(leoFilmId);
        return "redirect:/admin/index";
    }

    @RequestMapping("/toAdminFilmEdit")
    private String toAdminFilmEdit(LeoFilm leoFilm,Model model)  {
        List<LeoFilmType> leoFilmTypeList = typeService.findFilmType();
        List<LeoFilmForm> leoFilmFormList = typeService.findFilmForm();
        model.addAttribute("leoFilmTypeList",leoFilmTypeList);
        model.addAttribute("leoFilmFormList",leoFilmFormList);
        LeoFilm queryFilm = filmService.findFilmById(leoFilm.getLeoFilmId());
        model.addAttribute("queryFilm",queryFilm);
        return "admin/admin-film-edit";
    }

    @RequestMapping("/editAdminFilm")
    private String editAdminFilm(LeoFilm leoFilm)  {
        filmService.updateFilmById(leoFilm);
        return "redirect:/admin/index";
    }




}
