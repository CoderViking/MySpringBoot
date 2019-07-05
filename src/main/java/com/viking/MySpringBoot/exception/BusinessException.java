package com.viking.MySpringBoot.exception;


import com.viking.MySpringBoot.response.ResponseCode;

/**
 * Created by Viking on 2019/5/7.
 * 自定义业务异常类
 */
public class BusinessException extends RuntimeException   {
    private static final long serialVersionUID = -4321842465818992079L;
    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public BusinessException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BusinessException(String errMsg) {
        this.errCode = ResponseCode.BUSINESS_ERROR;
        this.errMsg = errMsg;
    }
}
