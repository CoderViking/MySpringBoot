package com.viking.MySpringBoot.exception;

/**
 * Created by Viking on 2018/12/27.
 * 自定义超时异常
 */
public class MyTimeOutException extends RuntimeException {
    public MyTimeOutException(String location, String msg) {
        this.location = location;
        this.msg = msg;
    }

    public MyTimeOutException(String msg) {
        this.msg = msg;
    }
    public MyTimeOutException(){

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
