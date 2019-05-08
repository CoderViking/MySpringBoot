package com.viking.MySpringBoot.config.mapper;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Created by Viking on 2019/5/7
 * 配置DruidStatViewServlet
 */
@WebServlet(
        urlPatterns= {"/druid/*"},
        initParams= {
                @WebInitParam(name="allow",value="127.0.0.1"),//允许访问HTML页面的IP
                @WebInitParam(name="loginUsername",value="root"),//登录用户名
                @WebInitParam(name="loginPassword",value="1234"),//登录密码
                @WebInitParam(name="resetEnable",value="true")// 允许HTML页面上的“Reset All”功能
        }
)
public class DruidStatViewServlet extends StatViewServlet implements Servlet {
    private static final long serialVersionUID = 1L;
}
