package com.viking.MySpringBoot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    public Object login(String account,String password){
        ModelAndView model = new ModelAndView();
        System.out.println("登录请求:account="+account+"\tpassword="+password);
        model.setViewName("my/login");
        return model;
    }
}
