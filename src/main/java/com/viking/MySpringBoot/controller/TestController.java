package com.viking.MySpringBoot.controller;

import com.viking.MySpringBoot.exception.SimpleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Viking on 2019/5/22
 * thymeleaf相关测试接口
 */
@RestController
@RequestMapping("test")
public class TestController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("login")
    public Object beforeLog(){
        ModelAndView model = new ModelAndView();
        model.setViewName("my/login");
        return model;
    }

    @RequestMapping("singIn")
    public Object login(String account, String password, HttpServletRequest request) throws Exception {
        log.info("登录请求:account="+account+"\tpassword="+password);
        if (account.equals("Admin")&&password.equals("123456")){
            Map<String,String> map = new HashMap<>();
            map.put("name","超级管理员");
            map.put("account",account);
            map.put("uid","5c2f9805-5392-4e13-8a94-ce3f9ff7f620");
            map.put("page","my/home::index");
            request.getSession().setAttribute("user",map);
            return "OK";
        }else throw new SimpleException("账号或密码有误");
    }
    @RequestMapping("index")
    @SuppressWarnings("unchecked")
    public Object index(HttpServletRequest request){
        Map<String,String> map = (Map<String, String>) request.getSession().getAttribute("user");
        if (map==null) return new ModelAndView("redirect:/test/login");
        ModelAndView model = new ModelAndView();
        model.addObject("username",map.get("name"));
        model.addObject("account",map.get("account"));
        model.addObject("uid",map.get("uid"));
        model.setViewName("my/myIndex");
        return model;
    }
    @RequestMapping("tables")
    public Object table(){
        ModelAndView model = new ModelAndView();
//        model.addObject("page","home::index");
        model.setViewName("my/tables");
        return model;
    }
    @RequestMapping("myIndex")
    @SuppressWarnings("unchecked")
    public Object myIndex(HttpServletRequest request){
        ModelAndView model = (ModelAndView)request.getSession().getAttribute("user");
        if (null == model) throw new SimpleException("请先登录后再操作");
        model.setViewName("/my/myIndex");
        return model;
    }
}
