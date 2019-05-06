package com.viking.MySpringBoot.config.muiltdatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by Viking on 2019/4/30
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactorySpringBoot",
        transactionManagerRef="transactionManagerSpringBoot",
        basePackages= { "com.viking.MySpringBoot.dao.springboot" })//设置dao（repo）所在位置
public class SpringBootConfig {
    @Autowired
    @Qualifier("springBootDatasource")
    private DataSource springBootDatasource;

    @Autowired
    @Qualifier("vendorProperties")
    private Map<String, Object> vendorProperties;

    @Bean(name = "entityManagerFactorySpringBoot")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySpringBoot (EntityManagerFactoryBuilder builder) {
        return builder.dataSource(springBootDatasource)
                .properties(vendorProperties)
                .packages("com.viking.MySpringBoot.pojo")
                .persistenceUnit("springBootPersistenceUnit")
                .build();
    }
    @Bean(name = "entityManagerSpringBoot")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySpringBoot(builder).getObject().createEntityManager();
    }

    @Bean(name = "transactionManagerSpringBoot")
    @Primary
    PlatformTransactionManager transactionManagerSpringBoot(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySpringBoot(builder).getObject());
    }
}
