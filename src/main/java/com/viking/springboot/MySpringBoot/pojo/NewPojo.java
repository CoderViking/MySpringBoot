package com.viking.springboot.MySpringBoot.pojo;

/**
 * Created by yanshuai on 2019/4/28
 */
public class NewPojo {
    public String name;
    public String favorite;
    public long tel;
    public String sex;
    public String addr;

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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "NewPojo{" +
                "name='" + name + '\'' +
                ", favorite='" + favorite + '\'' +
                ", tel=" + tel +
                ", sex='" + sex + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
