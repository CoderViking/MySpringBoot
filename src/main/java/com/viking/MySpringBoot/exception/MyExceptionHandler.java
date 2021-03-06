package com.viking.MySpringBoot.exception;

import com.viking.MySpringBoot.response.ResponseCode;
import com.viking.MySpringBoot.response.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Viking on 2019/7/16
 */
@ControllerAdvice
public class MyExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);
    private static final String DEFAULT_ERROR_MSG = "操作失败,请稍后再试!";
    private static final Integer DEFAULT_ERROR_CODE = 412;
    private static final String ERROR_STATUS_KEY = "javax.servlet.error.status_code";

    /**
     * 浏览器和客户端都返回json数据
     * @param e 异常对象
     * @return 自定义异常信息
     */
    @ResponseBody
    @ExceptionHandler(MyException.class)
    public Object myException(MyException e,HttpServletRequest request){
        log.error("自定义异常 [ " + (null == e.getCode()? DEFAULT_ERROR_CODE : e.getCode()) + " ] -------------------- " + (null == e.getMsg()? DEFAULT_ERROR_MSG : e.getMsg()));
        Map<String,Object> map = new HashMap<>();
        map.put("code", null == e.getCode()? DEFAULT_ERROR_CODE : e.getCode());
        map.put("errMsg", null == e.getMsg()? DEFAULT_ERROR_MSG : e.getMsg());
        request.getSession().setAttribute("errMsg",e.getMsg());
        return map;

    }
    /**
     * 浏览器和客户端自适应效果
     * @param e 异常对象
     * @return 自定义异常信息
     */
//    @ExceptionHandler(MyException.class)
//    public String myException(MyException e, HttpServletRequest request){
//        if (e.getCode() == null) e.setCode(DEFAULT_ERROR_CODE);
//        if (e.getMsg() == null) e.setMsg(DEFAULT_ERROR_MSG);
//        log.error("自定义异常 [ " + e.getCode() + " ] -------------------- " + e.getMsg());
//        request.setAttribute(ERROR_STATUS_KEY, e.getCode());
//        Map<String,Object> map = new HashMap<>();
//        map.put("msg", e.getMsg());
//        map.put("code", e.getCode());
//        request.setAttribute("myErrorAttributes",map);
//        return "forward:/error";
//
//    }
}
