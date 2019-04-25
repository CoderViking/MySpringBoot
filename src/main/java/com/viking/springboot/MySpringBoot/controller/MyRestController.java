package com.viking.springboot.MySpringBoot.controller;

import com.viking.springboot.MySpringBoot.pojo.MyPojo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Viking on 2019/4/25
 * web服务接口层
 */
@Controller
@RequestMapping("web")
public class MyRestController {

    @ResponseBody
    @RequestMapping(value = "user",method = RequestMethod.GET)
    public MyPojo getPojo(){
        MyPojo pojo = new MyPojo();
        pojo.setName("二白");
        pojo.setFavorite("play whit master");
        pojo.setSex("girl");
        pojo.setTel(100001001);
        return pojo;
    }
    @ResponseBody
    @RequestMapping(value = "user",method = RequestMethod.POST)
    public MyPojo getPojoPost(){
        MyPojo pojo = new MyPojo();
        pojo.setName("杰森");
        pojo.setFavorite("eat cat's food");
        pojo.setSex("boy");
        pojo.setTel(110000101);
        return pojo;
    }

    @RequestMapping("img")//失败
    public String showPicture(){
        return "/daojiangxing.jpg";

    }
}
