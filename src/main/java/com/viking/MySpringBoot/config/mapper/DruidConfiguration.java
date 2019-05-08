package com.viking.MySpringBoot.config.mapper;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by Viking on 2019/5/7
 * 注入DruidDataSource
 */
@Configuration
@Primary
public class DruidConfiguration {

    @Bean(name = "druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druidConfiguration(){
        return new DruidDataSource();
    }
}
