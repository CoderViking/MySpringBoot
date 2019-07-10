package com.viking.MySpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by yanshuai on 2019/7/9
 */
@Controller
public class FilterController {

    @RequestMapping("my/{name}")
    @SuppressWarnings("unchecked")
    public String myFilter(@PathVariable String name, Map<String,Object> map, HttpServletRequest request){
        Map<String,Object> model = (Map<String,Object>)request.getSession().getAttribute("user");
        if (null == model) return "/my/login";
        map.putAll(model);
        return "my/"+name;
    }
    @RequestMapping("my/public/{name}")
    @SuppressWarnings("unchecked")
    public Object myPublicFilter(@PathVariable String name){
        return "my/public/"+name;
    }

}
