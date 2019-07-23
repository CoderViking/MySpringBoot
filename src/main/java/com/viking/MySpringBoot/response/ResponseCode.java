package com.viking.MySpringBoot.response;


/**
 * Created by Viking on 2019/5/7.
 * 响应码
 */
public class ResponseCode {
    //成功
    public final static int SUCCESS = 200;
    //请求出错,业务出错
    public final static int BUSINESS_ERROR = 412;
    //程序出错
    public final static int ERROR = 500;
    //失败
    public final static int FAIL = 600;
    //无结果
    public final static int NORES = 300;
    //已存在
    public final static int EXIST = 400;
    //参数为空
    public final static int NOPA = 610;
    //无用户id
    public final static int NOUID = 620;
    //请求参数错误
    public final static int ERRPA = 630;

}
