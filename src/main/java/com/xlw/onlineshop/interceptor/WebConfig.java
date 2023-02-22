package com.xlw.onlineshop.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 登录拦截器的配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")//需要拦截的路径
                .addPathPatterns("/center/**")
                .addPathPatterns("/category/**")
                .addPathPatterns("/goodsCar/**")
                .addPathPatterns("/goods/**")
                .addPathPatterns("/order/**")
                .addPathPatterns("/toShoppingCenter")
                .addPathPatterns("/logout")
                .excludePathPatterns("/admin/index/login");
    }
}
