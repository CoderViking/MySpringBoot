package com.viking.springboot.MySpringBoot.controller;

import com.viking.springboot.MySpringBoot.dao.WeatherRepository;
import com.viking.springboot.MySpringBoot.pojo.MyPojo;
import com.viking.springboot.MySpringBoot.pojo.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Viking on 2019/4/25
 * web服务接口层
 */
@RestController
@RequestMapping("web")
public class MyRestController {
//    @Autowired
//    private MyPojo pojo;
    @Autowired
    private WeatherRepository weatherRepository;

    @RequestMapping(value = "user",method = RequestMethod.GET)
    public MyPojo getPojo(){
        MyPojo pojo = new MyPojo();
        pojo.setName("二白");
        pojo.setFavorite("play whit master");
        pojo.setSex("girl");
        pojo.setTel(100001001);
        return pojo;
    }
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
    public Object showPicture(){
        return "/daojiangxing.jpg";

    }

//    @RequestMapping("pojo")
//    public Object testMyPojo(){
//        System.out.println(pojo.toString());
//        return pojo;
//    }
    @RequestMapping("add")
    public Object addWeather(String weather,float temperature,String tip){
        Weather bean = new Weather();
        bean.setWeather(weather);
        bean.setTemperature(temperature);
        bean.setTip(tip);
        bean.setDate(new Date());
        weatherRepository.save(bean);
        return "OK";
    }
    @RequestMapping("list")
    public Object list(int pageNum,int pageSize){
        Page<Weather> page = weatherRepository.findAll(PageRequest.of(pageNum-1, pageSize));
        System.out.println(page.getContent());
        return page;
    }
}
