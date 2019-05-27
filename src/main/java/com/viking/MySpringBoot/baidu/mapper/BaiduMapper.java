package com.viking.MySpringBoot.baidu.mapper;

import com.viking.MySpringBoot.baidu.entity.Apad;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Viking on 2019/5/27
 */
public interface BaiduMapper {
    List<Apad> getApadList();
    void insertResult(@Param("param") List<Map<String,Object>> result);
}
