package com.viking.MySpringBoot.aop;


import com.viking.MySpringBoot.annation.Datasource;
import com.viking.MySpringBoot.config.datasource.DynamicDatasourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
/**
 * Created by Viking on 2019/5/10
 * 数据源切换Aspect
 */
@Aspect
@Component
public class DynamicDatasourceAspect {
    @Pointcut("@annotation(com.viking.MySpringBoot.annation.Datasource)")
    public void sAspect(){}

    @Before("sAspect()")
    public void beforeSwitchDS(JoinPoint point){
        Datasource datasource = getAnnotation(point,Datasource.class);
        String dataSource = datasource.value();
        DynamicDatasourceHolder.setDatasource(dataSource);// 切换数据源
    }

    private <T extends Annotation> T getAnnotation(JoinPoint jp, Class<T> clazz) {
        MethodSignature sign = (MethodSignature) jp.getSignature();
        Method method = sign.getMethod();
        return method.getAnnotation(clazz);
    }

    @After("@annotation(com.viking.MySpringBoot.annation.Datasource)")
    public void afterSwitchDS(JoinPoint point){
        DynamicDatasourceHolder.clearDatasource();
    }
}
