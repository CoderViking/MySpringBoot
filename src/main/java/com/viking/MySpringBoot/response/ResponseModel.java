package com.viking.MySpringBoot.response;

import java.io.Serializable;

/**
 * Created by Viking on 2019/5/19.
 * 响应model
 */
public class ResponseModel<T> implements Serializable {

    private String status;
    private String msg;
    private T data;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponseModel(){


    }
    public ResponseModel(String status){
        this.status=status;
    }
    public ResponseModel(String status, String msg){
        this.status=status;
        this.msg=msg;
    }
    public ResponseModel(String status, String msg,T data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }
}
