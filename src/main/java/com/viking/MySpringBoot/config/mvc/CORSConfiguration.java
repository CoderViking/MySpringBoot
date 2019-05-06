package com.viking.MySpringBoot.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Viking on 2019/5/6
 * 重写接口方法实现配置跨域
 * 在com.viking.MySpringBoot.config.MyConfiguration中已实现跨域配置不再重复配置
 */
//@Configuration
//@EnableWebMvc
public class CORSConfiguration implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //这里：是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }
}
