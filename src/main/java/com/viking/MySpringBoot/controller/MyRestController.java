package com.viking.MySpringBoot.controller;

import com.viking.MySpringBoot.pojo.MyPojo;
import com.viking.MySpringBoot.pojo.NewPojo;
import com.viking.MySpringBoot.entity.Weather;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by Viking on 2019/4/25
 * web服务接口层
 */
@RestController
@RequestMapping("web")
public class MyRestController {
//    @Autowired
//    private MyPojo pojo;
//    @Autowired
//    private WeatherRepository weatherRepository;

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

    @RequestMapping("add")
    public Object addWeather(String weather,float temperature,String tip){
        Weather bean = new Weather();
        bean.setWeather(weather);
        bean.setTemperature(temperature);
        bean.setTip(tip);
        bean.setDate(new Date());
//        weatherRepository.save(bean);
        return "OK";
    }
    @RequestMapping("json")
    public Object testJson(){
        MyPojo pojo = new MyPojo();
        pojo.setSex("girl");
        pojo.setName("Fast Json");
        pojo.setFavorite("Like swimming in the pool");
        pojo.setTel(1235484);
        List<MyPojo> list = new ArrayList<>();
        list.add(pojo);
        list.add(pojo);
        MyPojo nPo = new MyPojo();
        BeanUtils.copyProperties(pojo,nPo);
        System.out.println("nPo:"+nPo);
        Map<String,String> map = new HashMap<>();
        map.put("key","just a key of my Map");
        map.put("value","this is the value for key,in this map");
        System.out.println("map:"+map);
        Map<String,String> nMap = new HashMap<>();
        System.out.println("Before copy nMap:"+nMap);
        BeanUtils.copyProperties(map,nMap);
        System.out.println("after copy nMap:"+nMap);
        MyPojo mPo = new MyPojo();
        BeanUtils.copyProperties(pojo,mPo,"favorite");
        System.out.println("mPo:"+mPo);
        NewPojo wPo = new NewPojo();
        BeanUtils.copyProperties(pojo,wPo);
        System.out.println("wPo:"+wPo);
        return list;
    }
    @RequestMapping("get")
    public Object getWeather(Long id){
//        return weatherRepository.getWeather(id);
        return "OK";
    }
}
