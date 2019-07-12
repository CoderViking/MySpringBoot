package com.viking.MySpringBoot.mapper;

import com.viking.MySpringBoot.annation.Datasource;

import java.util.List;
import java.util.Map;

/**
 * Created by Viking on 2019/7/12
 */
public interface BookMapper {
    @Datasource("localServer")
    List<Map<String,Object>> listBook();
}
