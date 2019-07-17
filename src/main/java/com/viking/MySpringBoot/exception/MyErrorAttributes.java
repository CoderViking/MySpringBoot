package com.viking.MySpringBoot.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Created by Viking on 2019/7/16
 * 自定义错误属性
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String,Object> myErrorAttributes = (Map<String, Object>) webRequest.getAttribute("myErrorAttributes",0);
        if (null != myErrorAttributes) map.putAll(myErrorAttributes);
        return map;
    }
}
