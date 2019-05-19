package com.viking.MySpringBoot.mapper;

import com.viking.MySpringBoot.annation.Datasource;
import com.viking.MySpringBoot.entity.Weather;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Viking on 2019/5/7
 */
public interface FruitsMapper {
    List<Weather> getList();
    @Datasource("webspider")
    List<Map> getBook();
    void addWeather(@Param("param") Weather weather);
}
