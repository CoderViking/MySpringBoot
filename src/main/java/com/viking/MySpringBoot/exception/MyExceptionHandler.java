package com.viking.MySpringBoot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Viking on 2019/7/16
 */
@ControllerAdvice
public class MyExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);
    private static final String DEFAULT_ERROR_MSG = "操作失败,请稍后再试!";
    private static final Integer DEFAULT_ERROR_CODE = 471;

    /**
     * 浏览器和客户端都返回json数据
     * @param e 异常对象
     * @return 自定义异常信息
     */
    @ResponseBody
    @ExceptionHandler(MyException.class)
    public Object myException(MyException e){
        log.error("自定义异常 [ " + (null == e.getCode()? DEFAULT_ERROR_CODE : e.getCode()) + " ] -------------------- " + e.getMsg());
        Map<String,Object> map = new HashMap<>();
        map.put("code", null == e.getCode()? DEFAULT_ERROR_CODE : e.getCode());
        map.put("msg", null == e.getMsg()? DEFAULT_ERROR_MSG : e.getMsg());
        return map;

    }
}
