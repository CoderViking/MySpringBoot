package com.viking.MySpringBoot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.viking.MySpringBoot.mapper.FruitsMapper;
import com.viking.MySpringBoot.pojo.MyPojo;
import com.viking.MySpringBoot.pojo.NewPojo;
import com.viking.MySpringBoot.entity.Weather;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
    @Autowired
    private FruitsMapper fruitsMapper;

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
    public Object addWeather(Weather param){
        param.setDate(new Date());
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
    @RequestMapping("multi")
    public Object testMulti(){
        Map<String,Object> result = new HashMap<>();
        result.put("springBoot",fruitsMapper.getList());
        PageHelper.startPage(1,20);
        PageInfo<Map> pageInfo = new PageInfo<>(fruitsMapper.getBook());
        result.put("webSpider",pageInfo);
        result.put("springBoot2",fruitsMapper.getList());
        result.put("webSpider2",fruitsMapper.getBook());
        return result;
    }
    @RequestMapping("morse")
    public Object testNet(String code,String type) throws IOException {
        String param = "code=" + code + "&operate=" + type;
        URL url = new URL("https://tool.lu/morse/ajax.html");
        //发送请求
        HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
        httpurlconnection.setDoOutput(true);
        httpurlconnection.setRequestMethod("POST");//以post方式请求
        httpurlconnection.getOutputStream().write(param.getBytes("utf-8"));
        httpurlconnection.getOutputStream().flush();
        httpurlconnection.getOutputStream().close();
        //解析响应结果
        InputStream inputStream = httpurlconnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder resultBuffer = new StringBuilder();
        String tempLine;

        while ((tempLine = reader.readLine()) != null) {
            resultBuffer.append(tempLine);
        }
        return resultBuffer.toString();
    }
    @RequestMapping("ins")
    public Object insertMulti(Weather param){
        param.setDate(new Date());
        fruitsMapper.addWeather(param);
        return "OK";
    }
}
