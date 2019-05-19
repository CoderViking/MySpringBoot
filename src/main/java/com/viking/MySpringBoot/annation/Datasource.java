package com.viking.MySpringBoot.annation;

import com.viking.MySpringBoot.config.dynamicDatasource.DynamicDatasource;

import java.lang.annotation.*;

/**
 * Created by Viking on 2019/5/10
 * 自定义数据源切换注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited
public @interface Datasource {
    String value() default DynamicDatasource.DEFAULT;
}

