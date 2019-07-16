package com.viking.MySpringBoot.exception;

import com.alibaba.fastjson.JSONPObject;
import com.viking.MySpringBoot.response.ResponseCode;
import com.viking.MySpringBoot.response.ResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Viking on 2019/5/7.
 * 异常处理器
 */
@ControllerAdvice
public class SimpleExceptionHandler {

    private Logger logger =  LoggerFactory.getLogger(SimpleExceptionHandler.class);

    private static final String ERRORMSG="操作失败,请稍后再试!";

    @Autowired
    private HttpServletRequest request;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

//    @Bean
//    public HttpMessageConverters fastJsonConfigure(){
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        //日期格式化
//        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        converter.setFastJsonConfig(fastJsonConfig);
//        return new HttpMessageConverters(converter);
//    }

    @ExceptionHandler(SimpleException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Object operateSimple(SimpleException exception){
        logger.warn("simple 异常 [ " + exception.getLocation() + " ] -------------------- " + exception.getMsg());
        return  over(ResponseCode.BUSINESS_ERROR,exception.getMsg(),null);
    }
    @ExceptionHandler(MyTimeOutException.class)
    @ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
    @ResponseBody
    public Object MyTimeOutException(SimpleException exception){
        logger.warn("simple 异常 [ " + exception.getLocation() + " ] -------------------- " + exception.getMsg());
        return  over(ResponseCode.BUSINESS_ERROR,exception.getMsg(),null);
    }
//    @ResponseBody
//    @ResponseStatus(value = HttpStatus.OK)
//    @ExceptionHandler(FileException.class)
//    public Object operateFile(FileException exception){
//        logger.warn("file 异常 -------------------- " + exception.getMsg());
//        return over(ResponseCode.BUSINESS_ERROR,exception.getMsg(),false);
//    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Object operateException(BusinessException exception) {
        logger.error(exception.getMessage(), exception);
        logger.info("Exception 异常 [ " + new Date().toString()+ " ] ------------------ "+exception.getErrMsg());
        return over(exception.getErrCode(),exception.getErrMsg(),null);
//        return over(ResponseCode.BUSINESS_ERROR,ERRORMSG,null);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Object operateException(Exception exception) {
        logger.error(exception.getMessage(), exception);
//        exception.printStackTrace();
        logger.info("Exception 异常 [ " + new Date().toString()+ " ] ------------------ "+exception.getMessage());
//        return over(ResponseCode.BUSINESS_ERROR,exception.getMessage(),null);
        return over(ResponseCode.BUSINESS_ERROR,ERRORMSG,null);
    }

    private Object over(String status,String msg,Object data){
        if(StringUtils.isNotBlank(request.getParameter("callback"))){
            JSONPObject jsonpObject=new JSONPObject(request.getParameter("callback"));
            jsonpObject.addParameter(new ResponseModel(status, msg, data));
            return jsonpObject;
        }else {
            return new ResponseModel(status,msg,data);
        }
    }
}
