package com.viking.MySpringBoot.dao.springboot;

import com.viking.MySpringBoot.pojo.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Viking on 2019/4/27
 */
public interface WeatherRepository extends JpaRepository<Weather,Long> {

//    @Query(name = "select weather,temperature,tip,date from Weather where rid = ?1",nativeQuery=true)
//    Weather getWeather(Long id);
}
