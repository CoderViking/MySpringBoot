package com.viking.MySpringBoot.response;



import com.github.pagehelper.util.StringUtil;
import com.viking.MySpringBoot.exception.SimpleException;

import java.io.Serializable;

/**
 * Created by dell on 2016/5/19.
 */
public class ResponseModel<T> implements Serializable {

    private Integer status;
    private String msg;
    private T data;
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public ResponseModel(){}
    public ResponseModel(Integer status){
        this.status=status;
    }
    public ResponseModel(Integer status, String msg){
        this.status=status;
        this.msg=msg;
    }
    public ResponseModel(Integer status, String msg, T data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    public static <T> ResponseModel<T> setModel(T data){
        return new ResponseModel<T>(ResponseCode.SUCCESS,"成功",data);
    }
    public static <T> ResponseModel<T> setModel(Integer code,T data){
        return new ResponseModel<T>(code,"异常",data);
    }
    public static <T> ResponseModel<T> setErrModel(Integer code,String msg){
        return new ResponseModel<>(code,msg,null);
    }
    public static <T> ResponseModel<T> setErrModel(String msg){
        return new ResponseModel<>(ResponseCode.BUSINESS_ERROR,msg,null);
    }
    public static void isParamBlank(String param,String msg)throws SimpleException{
        if(StringUtil.isEmpty(param)) throw new SimpleException("业务层",msg+"不能为空");
    }
}
