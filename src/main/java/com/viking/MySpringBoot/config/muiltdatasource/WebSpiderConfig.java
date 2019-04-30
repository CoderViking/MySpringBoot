package com.viking.MySpringBoot.config.muiltdatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by yanshuai on 2019/4/30
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryWebSpider",
        transactionManagerRef="transactionManagerWebSpider",
        basePackages= { "com.viking.MySpringBoot.dao.webspider" })
public class WebSpiderConfig {

    @Autowired
    @Qualifier("webSpiderDatasource")
    private DataSource webSpriderDatasource;

    @Autowired
    @Qualifier("vendorProperties")
    private Map<String, Object> vendorProperties;

    @Bean(name = "entityManagerFactoryWebSpider")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryWebSpider (EntityManagerFactoryBuilder builder) {
        return builder.dataSource(webSpriderDatasource)
                .properties(vendorProperties)
                .packages("com.viking.MySpringBoot.pojo")
                .persistenceUnit("webSpiderPersistenceUnit")
                .build();
    }
    @Bean(name = "entityManagerWebSpider")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryWebSpider(builder).getObject().createEntityManager();
    }
    @Bean(name = "transactionManagerWebSpider")
    PlatformTransactionManager transactionManagerWebSpider(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryWebSpider(builder).getObject());
    }


}
