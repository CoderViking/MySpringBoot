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
            // 跨域配置
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
            // 静态资源映射
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
            }

//            // 自定义登录拦截器
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new HandlerInterceptor() {
//                    @Override
//                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//                        Object user = request.getSession().getAttribute("user");
//                        if (null == user && (request.getServletPath().endsWith(".html") || !request.getServletPath().contains("."))){
//                            request.setAttribute("msg","登录后获取访问权限~");
//                            // 请求转发方式跳转到登录页(url不变)
////                            request.getRequestDispatcher("/my/login").forward(request,response);
//                            // 重定向方式跳转到登录页(url变为重定向后的地址)
//                            response.sendRedirect("/my/login");
//                            return false;
//                        }else { return true;}
//                    }
//                    @Override
//                    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//                    }
//                    @Override
//                    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//                    }
//                }).addPathPatterns("/**").excludePathPatterns("/","/login","/error","/my/login","/my/login.html","/user/login","/user/register","/test/login.html");
//            }
        };
    }
}
