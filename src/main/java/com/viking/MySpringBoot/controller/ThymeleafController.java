package com.viking.MySpringBoot.controller;

import com.viking.MySpringBoot.entity.Weather;
import com.viking.MySpringBoot.mapper.FruitsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Viking on 2019/5/17
 * 测试thymeleaf模板
 */
@RestController
@RequestMapping("tl")
public class ThymeleafController {
    @Autowired
    private FruitsMapper mapper;

    @RequestMapping("home")
    public Object home(/*Map<String,String> param,HttpServletRequest request*/){
        /**
         * 有三种方式可以传递参数到thymeleaf页面
         * 第一种
         *      1.类注解为 @Controller
         *      2.方法返回值为 String
         *      3.方法中参数为对象类型且对象类型中字段名称与thymeleaf页面的参数名一致
         *      4.返回thymeleaf页面的路径+名称
         * 第二种
         *      1.类注解为 @Controller
         *      2.方法返回值为 String
         *      3.方法中有HttpServletRequest参数，在request中添加属性字段和值
         *      4.返回thymeleaf页面的路径+名称
         * 第三种
         *      1.类注解为 @RestController
         *      2.方法的返回值为 ModelAndViewer或者Object
         *      3.方法中可无参数
         *      4.返回值为ModelAndViewer类型的对象
         * */
//        param.put("name","Admin");
//        param.put("account","viking");
//        param.put("number","30");
//        return "/my/home";
//        request.setAttribute("number","20");
        ModelAndView model = new ModelAndView();
        model.addObject("username","Admin");
        model.addObject("account","Viking");
        model.addObject("number","18");
        model.addObject("isMarry",true);
        model.addObject("id",null);
        model.addObject("name","");
        model.addObject("China","<b>Chian</b>,USA,UK");
        List<Weather> list = mapper.getList();
        model.addObject("list",list);
        model.setViewName("my/home");
        System.out.println("访问页面");
        return model;
    }
    @RequestMapping("json")
    public Object testRestController(){
        Weather weather = new Weather();
        weather.setUserName("测试用户");
        weather.setTip("温馨提示");
        weather.setTemperature(36.4f);
        weather.setDate(LocalDateTime.now());
        return weather;
    }
}
