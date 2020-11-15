package com.graduation.filmcomment.controller;

import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.graduation.filmcomment.entity.LeoFilm;
import com.graduation.filmcomment.entity.LeoUser;
import com.graduation.filmcomment.service.UserService;
import com.graduation.filmcomment.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Resource
    private DefaultKaptcha captchaProducer;

    /**
     * 登录验证码SessionKey
     */
    public static final String LOGIN_VALIDATE_CODE = "login_validate_code";

    /**
     * 登录验证码图片
     */
    @RequestMapping(value = {"/generateCode"})
    public void loginValidateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommonUtil.validateCode(request, response, captchaProducer, LOGIN_VALIDATE_CODE);
    }

    /**
     * 跳转注册页面
     */
    @RequestMapping("register")
    public String register() {
        return "register";
    }

    /**
     * 判断注册的相关信息是否已经存在
     * @param leoUser
     * @return
     */
    @RequestMapping("isExistUserByCondition")
    @ResponseBody
    public Map<String, Object> isExistUserByCondition(LeoUser leoUser) {
        LeoUser existUser1 = userService.isExistUserName(leoUser.getLeoUserName());
        LeoUser existUser2 = userService.isExistUserPhone(leoUser.getLeoUserPhone());
        LeoUser existUser3 = userService.isExistUserEmail(leoUser.getLeoUserEmail());
        Map<String, Object> map = new HashMap();
        map.put("status", 0);
        if (existUser1 != null) {
            map.put("errorMsg", "用户名已存在");
        } else if (existUser2 != null) {
            map.put("errorMsg", "该手机号码已存在");
        } else if (existUser3 != null) {
            map.put("errorMsg", "该邮箱已存在");
        } else {
            map.put("status", 1);
        }
        return map;
    }

    /**
     * 注册用户，并且检查验证码是否正确
     */
    @RequestMapping("/checkCode")
    @ResponseBody
    public HashMap saveUser(HttpServletRequest request, @RequestParam("validateCode") String validateCode) {
        String loginValidateCode = request.getSession().getAttribute(LOGIN_VALIDATE_CODE).toString();
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (loginValidateCode == null) {
            map.put("status", null);
//            System.out.println("验证码过期");
        } else if (loginValidateCode.equals(validateCode)) {
            map.put("status", true);
//            System.out.println("验证码正确");
        } else if (!loginValidateCode.equals(validateCode)) {
            map.put("status", false);
//            System.out.println("验证码不正确");
        }
        map.put("code", 200);
        return map;
    }

    @RequestMapping("saveUser")
    public String saveUser(LeoUser leoUser) {
        userService.register(leoUser);
        return "redirect:login";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    /**
     * 通过密码方式进行登录
     * @param leoUser
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("loginUser")
    public String loginUser(LeoUser leoUser, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        LeoUser userExist = userService.loginUser(leoUser);
        if (userExist != null) {
            session.setAttribute("user", userExist);
            return "redirect:/intro/main";
        } else {
            model.addAttribute("errorMsg", "用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/intro/main";
    }

    @RequestMapping("logout2")
    public String logout2(Integer id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/intro/filmDetail?id=" + id;
    }

    @RequestMapping("userInfo")
    public String userInfo(Integer leoUserId, Model model) {
        List<LeoFilm> leoFilmFiveWantSee = userService.findFiveWantSeeFilmByUserId(leoUserId);
        List<LeoFilm> leoFilmFiveSeen = userService.findFiveSeenFilmByUserId(leoUserId);
        LeoUser leoUser = userService.findUserById(leoUserId);
        model.addAttribute("leoUser", leoUser);
        model.addAttribute("leoFilmFiveSeen", leoFilmFiveSeen);
        model.addAttribute("leoFilmFiveWantSee", leoFilmFiveWantSee);
        return "userInfo";

    }

    @GetMapping("modifyUserInfo")
    public String modifyUserInfo(Integer leoUserId, Model model) {
        LeoUser leoUser = userService.findUserById(leoUserId);
        model.addAttribute("leoUser", leoUser);
        return "modifyUserInfo";
    }


    /**
     * 判断要修改的用户名是否已经存在
     * @param modifyUserName 即将修改的用户名
     * @param leoUserName    自己本身的用户名
     * @return
     */
    @RequestMapping("checkUserNameIsNotExist")
    @ResponseBody
    public Map<String, Object> checkUserNameIsNotExist(String modifyUserName,String leoUserName) {
        LeoUser leoUser = userService.isExistUserName(modifyUserName);
        Map<String, Object> map = new HashMap<>();
        if (leoUser != null) {
            if (leoUser.getLeoUserName().equals(leoUserName)) { //（除去本身自己的用户名）
                map.put("status", 1);//code 1：成功
            } else {
                map.put("status", 0);//code 0：失败
            }
        } else {
            map.put("status", 1);//code 1：成功
        }
        return map;
    }

    /**
     * 提交修改个人信息的表单
     * @param leoUser
     * @param model
     * @return
     */
    @RequestMapping("modifyForm")
    public String modifyForm(LeoUser leoUser, Model model) {
        userService.modifyUserInfo(leoUser);
        LeoUser leoUserModel = userService.findUserById(leoUser.getLeoUserId());
        model.addAttribute("leoUser", leoUserModel);
        return "modifyUserInfo";
    }


    /**
     * 更新自己的头像
     * @param file
     * @param leoUserId
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadHeadImg")
    @ResponseBody
    private HashMap<String, Object> uploadHeadImg(MultipartFile file, Integer leoUserId) throws IOException {

        HashMap<String, Object> map = new HashMap();
        if (file != null && !file.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            file.transferTo(new File("D:\\IdeaProjects\\filmComment\\src\\main\\resources\\static\\img\\" + uuid + file.getOriginalFilename()));
            map.put("code", 0);
            userService.updateUserImage(leoUserId, "/img/" + uuid + file.getOriginalFilename());
            return map;
        } else {
            map.put("code", -1);
            return map;
        }
    }


//    -----------------Admin------------------



    @RequestMapping("/toUserList")
    private String toUserList() {
        return "/admin/admin-user-list";
    }

    @RequestMapping("/queryUserList")
    @ResponseBody
    private Map<String, Object> queryUserList(@RequestParam(value = "page") Integer page, @RequestParam(value = "limit") Integer limit, LeoUser leoUser) {
        PageInfo<LeoUser> pageInfo = userService.findAll(page, limit, leoUser);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        return map;
    }

    /**
     * 禁用/开启用户的评论状态
     * @param leoUserId
     * @param leoUserStatus
     * @return
     */
    @GetMapping("/updateUserStatus")
    private String updateUserStatus(Integer leoUserId, Boolean leoUserStatus) {
        userService.updateUserStatus(leoUserId, leoUserStatus);
        return "/admin/admin-user-list";
    }

}