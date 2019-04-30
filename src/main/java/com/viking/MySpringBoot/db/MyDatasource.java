package com.viking.MySpringBoot.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * Created by Viking on 2019/4/29
 * 配置自定义Datasource数据源
 */
public class MyDatasource {

    @Bean
    @ConfigurationProperties(prefix = "my-app.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "my-app.datasourcec")
    public HikariDataSource hikariDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
