package com.viking.springboot.MySpringBoot.dao;

import com.viking.springboot.MySpringBoot.pojo.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Viking on 2019/4/27
 */
public interface WeatherRepository extends JpaRepository<Weather,Long> {
}
