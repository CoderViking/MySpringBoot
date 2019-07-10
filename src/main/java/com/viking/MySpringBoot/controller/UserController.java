package com.viking.MySpringBoot.controller;

import com.viking.MySpringBoot.exception.SimpleException;
import com.viking.MySpringBoot.response.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by yanshuai on 2019/7/10
 */
@Controller
@RequestMapping("user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("login")
    @ResponseBody
    public Object login(String account, String password, HttpServletRequest request){
        log.info("登录请求:account="+account+"\tpassword="+password);
        if (account.length() >= 3 && password.length() >= 6){
            ModelAndView model = new ModelAndView();
            model.addObject("username","超级管理员");
            model.addObject("account",account);
            model.addObject("uid","5c2f9805-5392-4e13-8a94-ce3f9ff7f620");
            request.getSession().setAttribute("user",model.getModel());
            return ResponseModel.setModel("success");
        }return ResponseModel.setErrModel("账号或密码错误");
    }
    @RequestMapping("home")
    @SuppressWarnings("unchecked")
    public Object index(Map<String,Object> map,HttpServletRequest request){
        Map<String,Object> model = (Map<String,Object>) request.getSession().getAttribute("user");
        if (null == model) return "/my/login";
        map.putAll(model);
        return "/my/myIndex";
    }
}
