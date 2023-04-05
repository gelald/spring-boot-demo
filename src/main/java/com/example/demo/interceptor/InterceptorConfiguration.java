package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author WuYingBin
 * date: 2023/4/5
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    private HandlerInterceptorOne handlerInterceptorOne;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //这册拦截器
        registry.addInterceptor(handlerInterceptorOne)
                //设置拦截的路径
                .addPathPatterns("/bad/*")
                //设置不拦截的路径（排除这些路径）
                .excludePathPatterns("/bad/test");
    }
}
