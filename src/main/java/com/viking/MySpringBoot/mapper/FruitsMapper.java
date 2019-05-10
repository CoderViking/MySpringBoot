package com.viking.MySpringBoot.mapper;

import com.viking.MySpringBoot.annation.Datasource;
import com.viking.MySpringBoot.entity.Weather;

import java.util.List;
import java.util.Map;

/**
 * Created by Viking on 2019/5/7
 */
public interface FruitsMapper {
    Weather getList();
    @Datasource("webspider")
    List<Map<String,Object>> getBook();
}
