package com.viking.MySpringBoot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    public Object login(String account,String password) throws Exception {
        System.out.println("登录请求:account="+account+"\tpassword="+password);
//        model.setViewName("my/index");
        if (account.equals("Admin")&&password.equals("123456")){
            Map<String,Object> result = new HashMap<>();
            result.put("name","超级管理员");
            result.put("account",account);
            result.put("uid","5c2f9805-5392-4e13-8a94-ce3f9ff7f620");
            return result;
        }else throw new Exception("账号或密码有误");
    }
    @RequestMapping("index")
    public Object index(){
        ModelAndView model = new ModelAndView();
        model.setViewName("my/index");
        return model;
    }
}
