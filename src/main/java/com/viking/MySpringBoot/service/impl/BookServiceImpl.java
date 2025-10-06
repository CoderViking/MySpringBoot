package com.viking.MySpringBoot.service.impl;

import com.viking.MySpringBoot.mapper.BookMapper;
import com.viking.MySpringBoot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Viking on 2019/7/12
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper mapper;

    @Override
    public Map<String, Object> listBook() {
        List<Map<String, Object>> list =  mapper.listBook();
        Map<String, Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",list.size());
        return map;
    }
}
