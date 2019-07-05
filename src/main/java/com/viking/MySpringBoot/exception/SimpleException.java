package com.viking.MySpringBoot.exception;

/**
 * Created by Viking on 2019/5/8.
 * 通用异常
 */
public class SimpleException extends RuntimeException{

    public SimpleException(String location, String msg) {
        this.location = location;
        this.msg = msg;
    }

    public SimpleException(String msg) {
        this.msg = msg;
    }

    private static final long serialVersionUID = -4321842465818992079L;

    private String location;
    private String msg;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
