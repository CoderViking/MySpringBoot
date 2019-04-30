package com.viking.MySpringBoot.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Created by yanshuai on 2019/4/29
 */
public class MuiltDatasource {
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.first")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.first.configuration")
    public HikariDataSource firstDataSource() {
        return firstDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

//    @Bean
//    @ConfigurationProperties("app.datasource.second")
//    public BasicDataSource secondDataSource() {
//        return DataSourceBuilder.create().type(BasicDataSource.class).build();
//    }
}
