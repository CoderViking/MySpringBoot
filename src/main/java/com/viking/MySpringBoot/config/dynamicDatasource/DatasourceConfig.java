package com.viking.MySpringBoot.config.dynamicDatasource;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Viking on 2019/5/9
 */
@Configuration
@MapperScan(basePackages = "com.viking.**.mapper")
public class DatasourceConfig {
    //数据源1
    @Bean(name = "localServer")
    @ConfigurationProperties(prefix = "spring.datasource.local-server") // application.properties中对应属性的前缀
    public DataSource localServer() {
        return DruidDataSourceBuilder.create().build();
    }//使用Druid连接池创建数据源

    //数据源2
    @Bean(name = "linuxServer")
    @ConfigurationProperties(prefix = "spring.datasource.linux-server") // application.properties中对应属性的前缀
    public DataSource linuxServer() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDatasource dynamicDataSource = DynamicDatasource.getInstance();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(linuxServer());
        // 配置多数据源
        Map<Object, Object> datasourceMap = new HashMap<>();
        datasourceMap.put("localServer", localServer());
        datasourceMap.put("linuxServer", linuxServer());

        dynamicDataSource.setTargetDataSources(datasourceMap);
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事物
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
