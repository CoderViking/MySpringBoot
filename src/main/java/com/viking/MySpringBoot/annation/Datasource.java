package com.viking.MySpringBoot.annation;

import com.viking.MySpringBoot.config.dynamicDatasource.DynamicDatasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yanshuai on 2019/5/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.PACKAGE})
public @interface Datasource {
    String value() default DynamicDatasource.DEFAULT;
}
