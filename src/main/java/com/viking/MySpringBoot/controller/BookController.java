package com.viking.MySpringBoot.controller;

import com.viking.MySpringBoot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Viking on 2019/7/12
 */
@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookService service;

    @RequestMapping("list")
    public Object listBook(){
        return service.listBook();
    }


}
