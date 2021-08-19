package com.dinesh.UserService.interceptor;

import com.dinesh.UserService.entity.Log;
import com.dinesh.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private UserService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("request = " + request.getRequestURI() + " " + request.getRequestURI().split("/")[1]);
        if(request.getMethod().equals("POST") && request.getRequestURI().split("/")[1].equals("users"))
            service.insertLog(new Log(request.getRequestURI(), response.getStatus()));
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}