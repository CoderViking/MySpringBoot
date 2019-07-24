package com.viking.MySpringBoot.controller;

import com.viking.MySpringBoot.entity.Blog;
import com.viking.MySpringBoot.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Viking on 2019/7/24
 * 测试Es使用
 */
@RestController
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @PostMapping("add")
    public Object add(Blog blog){
//        Blog blog = new Blog();
//        blog.s
        try {
            blog.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2019-07-24"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        blogRepository.save(blog);
        return "Ok";
    }
}
