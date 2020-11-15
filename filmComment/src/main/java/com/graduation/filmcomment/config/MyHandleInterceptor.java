package com.graduation.filmcomment.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //未登录状态，不允许进入修改个人信息页面
        Object user = request.getSession().getAttribute("user");
        if(user==null){
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("/intro/main").forward(request,response);
            return false;
        }
        return true;
    }

}
