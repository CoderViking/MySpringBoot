package com.viking.springboot.MySpringBoot.db;

import org.springframework.boot.autoconfigure.AbstractDependsOnBeanFactoryPostProcessor;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;

/**
 * {@link BeanFactoryPostProcessor} that can be used to dynamically declare that all
 * {@link EntityManagerFactory} beans should "depend on" one or more specific beans.
 *
 * @author Marcel Overdijk
 * @author Dave Syer
 * @author Phillip Webb
 * @author Andy Wilkinson
 * @since 1.1.0
 * @see BeanDefinition#setDependsOn(String[])
 */
/**
 * Created by Viking on 2019/4/29
 */
public class EntityManagerFactoryDependsOnPostProcessor extends AbstractDependsOnBeanFactoryPostProcessor  {
    public EntityManagerFactoryDependsOnPostProcessor(String... dependsOn) {
        super(EntityManagerFactory.class, AbstractEntityManagerFactoryBean.class,
                dependsOn);
    }
}
