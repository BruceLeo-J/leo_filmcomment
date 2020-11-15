package com.graduation.filmcomment.controller;

import com.graduation.filmcomment.entity.LeoUser;
import com.graduation.filmcomment.service.SendSms;
import com.graduation.filmcomment.service.UserService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin  //跨域支持
public class SmsController {

    @Autowired
    private SendSms sendSms;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private UserService userService;

    @GetMapping("/send")
    @ResponseBody
    public Map sendPhone(String loginPhone){
        Map<String,Object> map = new HashMap<>();

            //通过手机号码判断所属用户是否存在
            LeoUser leoUser = userService.findUserByPhone(loginPhone);
            if (leoUser==null){
                map.put("exist",0);
                map.put("msg","该手机号码所属用户不存在!");
            }else {

                //真正进入redis业务！
                //调用发送方法（模拟真实redis业务）
                String code = redisTemplate.opsForValue().get(loginPhone);
                if (!StringUtil.isNullOrEmpty(code)){
                    map.put("status",0);
                    map.put("msg","验证码已存在，还未过期");
                }else {
                    //生成验证码，并存入redis中
                    code = UUID.randomUUID().toString().substring(0, 4);
                    Map<String,Object> param = new HashMap<>();
                    param.put("code",code);

                    boolean isSend = sendSms.send(loginPhone, "SMS_205133274", param);//传参调用短信服务接口
                    if (isSend){
                        redisTemplate.opsForValue().set(loginPhone,code,3, TimeUnit.MINUTES);//将手机和验证码存入redis
                        map.put("status",1);
                        map.put("msg","发送成功");
                    }else {
                        map.put("status",-1);
                        map.put("msg","发送失败");
                    }
                }
            }
            return map;
    }


    /**
     * 通过手机号码加短信方式进行登陆
     * @param loginPhone
     * @param loginCode
     * @param request
     * @return
     */
    @RequestMapping("/checkLoginByPhone")
    @ResponseBody
    public Map checkLoginByPhone(String loginPhone, String loginCode, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        if(StringUtil.isNullOrEmpty(loginPhone)||StringUtil.isNullOrEmpty(loginCode)){
            map.put("code",-2);
            map.put("msg","请输入完整信息");
        }else {
            String code = redisTemplate.opsForValue().get(loginPhone);
            if (StringUtil.isNullOrEmpty(code)){
                map.put("code",0);
                map.put("msg","验证码已过期");
            }else {
                if (!code.equals(loginCode)){
                    map.put("code",-1);
                    map.put("msg","验证码错误");
                }else {
                    map.put("code",1);
                    LeoUser leoUser = userService.findUserByPhone(loginPhone);
                    request.getSession().setAttribute("user",leoUser);
                }
            }
        }
        return map;
    }
}