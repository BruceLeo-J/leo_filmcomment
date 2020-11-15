package com.graduation.filmcomment.controller;


import com.graduation.filmcomment.entity.LeoAdmin;
import com.graduation.filmcomment.service.AdminService;
import com.graduation.filmcomment.utils.SaltUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/toRegister")
    private String toRegister() {
        return "/admin/register";
    }

    /**
     * 注册一个管理员账号
     * @param leoAdmin
     * @return
     */
    @RequestMapping("/submitRegisterForm")
    private String submitRegisterForm(LeoAdmin leoAdmin) {
        //1.生成随机盐
        String salt = SaltUtils.addSalt(8);
        //2.将随机盐值存入数据
        leoAdmin.setLeoAdminSalt(salt);
        //3.将明文密码进行md5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(leoAdmin.getLeoAdminPassword(),salt,1024);
        //4.将加密后的密码存入数据
        leoAdmin.setLeoAdminPassword(md5Hash.toHex());
        adminService.save(leoAdmin);

        return "redirect:/admin/toLogin";
    }

    @RequestMapping("/toLogin")
    private String toLogin() {
        return "/admin/login";
    }

    /**
     * 登录管理系统，对管理员进行身份认证与授权
     * @param leoAdmin
     * @return
     */
    @RequestMapping("/submitLoginForm")
    private String submitLoginForm(LeoAdmin leoAdmin) {

        //通过安全管理器工具，获取主体对象
        Subject subject = SecurityUtils.getSubject();

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(leoAdmin.getLeoAdminUsername(), leoAdmin.getLeoAdminPassword());
            subject.login(token);
            //代码方式
            if (subject.hasRole("admin")) {
                System.out.println("保存订单!");
            }else{
                System.out.println("无权访问!");
            }

            return "redirect:/admin/index";

        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }



        return "/admin/login";
    }

    @RequestMapping("/index")
    private String index(){
        return "admin/index";
    }

    @RequestMapping("/toWelcome")
    private String toWelcome(){
        return "admin/welcome";
    }

    @RequestMapping("/toAdminLogin")
    private String toAdminLogin(){
        return "admin/login";
    }

    @RequestMapping("/toAdminList")
    private String toAdminList(Model model){
        List<LeoAdmin> leoAdminList =  adminService.findAll();
        model.addAttribute("leoAdminList",leoAdminList);
        return "admin/admin-list";
    }

}
