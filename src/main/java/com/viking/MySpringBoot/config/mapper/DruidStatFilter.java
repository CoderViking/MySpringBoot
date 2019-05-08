package com.viking.MySpringBoot.config.mapper;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by Viking on 2019/5/7
 * 配置DruidStatFilter
 */
@WebFilter(
        filterName="druidWebStatFilter",
        urlPatterns= {"/*"},
        initParams= {
                @WebInitParam(name="exclusions",value="*.js,*.jpg,*.png,*.gif,*.ico,*.css,/druid/*")//配置本过滤器放行的请求后缀
        }
)
public class DruidStatFilter extends WebStatFilter {
}
