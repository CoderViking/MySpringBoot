package com.viking.MySpringBoot.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Viking on 2019/4/25
 */
//@Configuration
@ConfigurationProperties(prefix = "my-config.pojo")
//@ComponentScan
public class MyPojo {
//    @Value("${my-config.pojo.name}")
    public String name;
//    @Value("${my-config.pojo.favorite}")
    public String favorite;
//    @Value("${my-config.pojo.tel}")
    public long tel;
//    @Value("${my-config.pojo.sex}")
    public String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "MyPojo{" +
                "name='" + name + '\'' +
                ", favorite='" + favorite + '\'' +
                ", tel=" + tel +
                ", sex='" + sex + '\'' +
                '}';
    }
}
