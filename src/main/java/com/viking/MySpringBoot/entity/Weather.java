package com.viking.MySpringBoot.entity;

import java.time.LocalDateTime;

/**
 * Created by Viking on 2019/4/27
 * 测试springBoot中jpa的url参数
 */
public class Weather {
    private long rid;
    private String weather;
    private float temperature;
    private String tip;
    private LocalDateTime date;
    private String userName;

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
