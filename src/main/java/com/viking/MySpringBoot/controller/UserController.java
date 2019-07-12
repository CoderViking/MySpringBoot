package com.viking.MySpringBoot.controller;

import com.viking.MySpringBoot.exception.SimpleException;
import com.viking.MySpringBoot.response.ResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Viking on 2019/7/10
 */
@Controller
@RequestMapping("user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("login")
    @ResponseBody
    public Object login(String account, String password, HttpSession session){
        log.info("登录请求:account="+account+"\tpassword="+password);
        if (account.length() >= 3 && password.length() >= 6){
            ModelAndView model = new ModelAndView();
            model.addObject("username","Admin".equals(account)?"超级管理员":"用户"+account);
            model.addObject("account",account);
            model.addObject("uid","5c2f9805-5392-4e13-8a94-ce3f9ff7f620");
            session.setAttribute("user",model.getModel());
            return ResponseModel.setModel("success");
        }return ResponseModel.setErrModel("账号或密码错误");
    }
    @RequestMapping("logout")
    @ResponseBody
    public Object logout(String account, String uid, HttpSession session){
        log.info("退出登录:account="+account+"\tuid="+uid);
        if (session.getAttribute("user")!=null){
            session.removeAttribute("user");
            return ResponseModel.setModel("success");
        }else return ResponseModel.setErrModel("操作失败");
    }
    @RequestMapping("register")
    @ResponseBody
    public Object register(String name, String password, String email){
        if (StringUtils.isBlank(name)||StringUtils.isBlank(password)||StringUtils.isBlank(email)){
            return ResponseModel.setErrModel("请完善资料后再提交");
        }else {
            log.info("注册用户:name="+name+"\tpassword="+password+"\temail="+email);
            return ResponseModel.setModel("success");
        }
    }
    @RequestMapping("home")
    @SuppressWarnings("unchecked")
    public Object index(Map<String,Object> map,HttpServletRequest request){
        Map<String,Object> model = (Map<String,Object>) request.getSession().getAttribute("user");
//        if (null == model) return "/my/login";
        map.putAll(model);
        return "/my/myIndex";
    }
}
