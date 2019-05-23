package com.viking.MySpringBoot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanshuai on 2019/5/22
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("login")
    public Object beforeLog(){
        ModelAndView model = new ModelAndView();
        model.setViewName("my/login");
        return model;
    }

    @RequestMapping("singIn")
    public Object login(String account, String password, HttpServletRequest request) throws Exception {
        System.out.println("登录请求:account="+account+"\tpassword="+password);
//        model.setViewName("my/index");
        if (account.equals("Admin")&&password.equals("123456")){
            Map<String,String> map = new HashMap<>();
            map.put("name","超级管理员");
            map.put("account",account);
            map.put("uid","5c2f9805-5392-4e13-8a94-ce3f9ff7f620");
            request.getSession().setAttribute("user",map);
            return "OK";
        }else throw new Exception("账号或密码有误");
    }
    @RequestMapping("index")
    @SuppressWarnings("unchecked")
    public Object index(HttpServletRequest request){
        Map<String,String> map = (Map<String, String>) request.getSession().getAttribute("user");
        ModelAndView model = new ModelAndView();
        model.addObject("name",map.get("name"));
        model.addObject("account",map.get("account"));
        model.addObject("uid",map.get("uid"));
        model.setViewName("my/index");
        return model;
    }
}
