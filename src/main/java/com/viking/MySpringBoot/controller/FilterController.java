package com.viking.MySpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yanshuai on 2019/7/9
 */
@Controller
public class FilterController {

    @RequestMapping("my/{name}")
    public String myFilter(@PathVariable String name){
        return "my/"+name;
    }
    @RequestMapping("my/public/{name}")
    public String myPublicFilter(@PathVariable String name){
        return "my/public/"+name;
    }

}
