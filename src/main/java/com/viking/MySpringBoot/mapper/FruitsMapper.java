package com.viking.MySpringBoot.mapper;

import com.viking.MySpringBoot.entity.Weather;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Viking on 2019/5/7
 */
public interface FruitsMapper {
    List<Weather> getAllWeather();

    void insert(@Param("param") Weather weather);
}
