package com.viking.springboot.MySpringBoot.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yanshuai on 2019/4/25
 */
//@ConfigurationProperties(prefix = "my-config.pojo")
public class MyPojo {
    private String name;
    private String favorite;
    private long tel;
    private String sex;

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
