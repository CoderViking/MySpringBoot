package com.viking.MySpringBoot.aop;


import com.viking.MySpringBoot.annation.Datasource;
import com.viking.MySpringBoot.config.dynamicDatasource.DatasourceContextHolder;
import com.viking.MySpringBoot.config.dynamicDatasource.DynamicDatasource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
/**
 * Created by yanshuai on 2019/5/10
 *
 */
@Aspect
@Component
public class DynamicDatasourceAspect {
    @Pointcut("@annotation(com.viking.MySpringBoot.annation.Datasource)")
    public void sAspect(){}

    @Before("sAspect()")
    public void beforeSwitchDS(JoinPoint point){
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        String dataSource = DynamicDatasource.DEFAULT;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@DS注解
            if (method.isAnnotationPresent(Datasource.class)) {
                Datasource annotation = method.getAnnotation(Datasource.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 切换数据源
        DatasourceContextHolder.setDB(dataSource);
    }

    @After("@annotation(com.viking.MySpringBoot.annation.Datasource)")
    public void afterSwitchDS(JoinPoint point){
        DatasourceContextHolder.clearDB();
    }
}
