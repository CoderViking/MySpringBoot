package com.viking.MySpringBoot.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Viking on 2019/4/26
 * 匿名内部类实现跨域配置、拦截器配置
 */
@Configuration
public class MvcConfiguration {

    @Bean
    public WebMvcConfigurer crossConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
            }

            // 自定义登录拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        Object user = request.getSession().getAttribute("user");
                        if (null == user && (request.getServletPath().contains("html") || !request.getServletPath().contains("."))){
                            request.setAttribute("msg","登录后获取访问权限~");
                            request.getRequestDispatcher("/my/login").forward(request,response);
                            return false;
                        }else { return true;}
                    }
                    @Override
                    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
                    }
                    @Override
                    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
                    }
                }).addPathPatterns("/**").excludePathPatterns("/","/my/login","/my/login.html","/user/login","/test/login.html");
            }
        };
    }
}
