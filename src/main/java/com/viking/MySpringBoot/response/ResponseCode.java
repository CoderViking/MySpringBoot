package com.viking.MySpringBoot.response;


/**
 * Created by Viking on 2019/5/7.
 * 响应码
 */
public class ResponseCode {
    //成功
    public final static String SUCCESS = "200";
    //请求出错,业务出错
    public final static String BUSINESS_ERROR = "412";
    //程序出错
    public final static String ERROR = "500";
    //失败
    public final static String FAIL = "600";
    //无结果
    public final static String NORES = "300";
    //已存在
    public final static String EXIST = "400";
    //参数为空
    public final static String NOPA = "610";
    //无用户id
    public final static String NOUID = "620";
    //请求参数错误
    public final static String ERRPA = "630";

}
