# MySpringBoot
That is a project for learning the springBoot<br/>
#### [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/html/index.html)
##### [31. Working with SQL Databases](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html)
* [31.1. Configure a DataSource](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-configure-datasource)
    * [31.1.1. Embedded Database Support](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-embedded-database-support)
    * [31.1.2. Connection to a Production Database](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-connect-to-production-database)
    * [31.1.3. Connection to a JNDI DataSource](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-connecting-to-a-jndi-datasource)
* [31.2. Using JdbcTemplate](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-using-jdbc-template)
* [31.3. JPA and Spring Data JPA](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-jpa-and-spring-data)
    * [31.3.1. Entity Classes](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-entity-classes)
    * [31.3.2. Spring Data JPA Repositories](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-spring-data-jpa-repositories)
    * [31.3.3. Creating and Dropping JPA Databases](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-creating-and-dropping-jpa-databases)
    * [31.3.4. Open EntityManager in View](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-jpa-in-web-environment)
* [31.4. Spring Data JDBC](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-data-jdbc)
* [31.5. Using H2’s Web Console](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-sql-h2-console)
    * [31.5.1. Changing the H2 Console’s Path](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html#boot-features-sql-h2-console-custom-path)
* 31.6. Using jOOQ
    * 31.6.1. Code Generation
    * 31.6.2. Using DSLContext
    * 31.6.3. jOOQ SQL Dialect
    * 31.6.4. Customizing jOOQ<br/>
    
__[The demo of Learning springBoot](https://mp.weixin.qq.com/s?__biz=MzUzODcwMDIzOQ==&mid=2247487355&idx=1&sn=81d536ac18f88af3f348dd4f775a41af)__

[SpringMVC自动配置](https://docs.spring.io/spring-boot/docs/1.5.10.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

[配置文件能配置的属性参照](https://docs.spring.io/spring-boot/docs/1.5.9.RELEASE/reference/htmlsingle/#common-application-properties)

**springBoot 自动注入原理**
* 1.在SpringBootApplication.java类中添加一个@EnableAutoConfiguration注解
* 2.该注解会在spring-boot-autoconfigure包下的WEB-INF目录下去加载spring.factories文件中的所有xxxAutoConfiguration组件
* 3.在每个组件中都有一个@Configuration注解,表示这个类是一个配置类;@ConditionalOnXXX这个注解是判断这个配置类加载的条件，条件成立才生效，否则不生效;
<br/>&nbsp;&nbsp;&nbsp;若自动配置组件类中包含@EnableConfigurationProperties这个注解或者有private final MongoProperties properties;这样的属性，
<br/>&nbsp;&nbsp;&nbsp;那么表示这个自动配置组件是可以通过读取配置文件来获取用户自定义的配置属性的，具体的配置字段名都在xxxProperties类中
* 4.一但这个配置类生效；这个配置类就会给容器中添加各种组件；这些组件的属性是从对应的properties类中获取的，这些类里面的每一个属性又是和配置文件绑定的；
* 5.所有在配置文件中能配置的属性都是在xxxProperties类中封装着；配置文件能配置什么就可以参照某个功能对应的这个属性类

**总结**
* 1.SpringBoot启动会加载大量的自动配置类
* 2.在需要时我们看一下需要的功能有没有SpringBoot默认写好的自动配置类；
* 3.然后再看一下这个自动配置类中到底配置了哪些组件；（只要我们要用的组件有，我们就不需要再来配置了）
* 4.给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。我们就可以在配置文件中指定这些属性的值；
* 5.xxxAutoConfiguration: 自动配置类 给容器中添加组件
* 6.xxxProperties: 封装配置文件中相关属性

 