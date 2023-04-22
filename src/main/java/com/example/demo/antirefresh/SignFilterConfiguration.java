package com.example.demo.antirefresh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuYingBin
 * date: 2023/4/22
 */
@Configuration
public class SignFilterConfiguration {
    @Value("${sign.maxTime}")
    private String signMaxTime;

    //filter中的初始化参数
    private final Map<String, String> initParametersMap = new HashMap<>();

    @Bean
    public FilterRegistrationBean<Filter> contextFilterRegistrationBean() {
        initParametersMap.put("signMaxTime", signMaxTime);
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(signFilter());
        registration.setInitParameters(initParametersMap);
        registration.addUrlPatterns("/sign/*");
        registration.setName("SignFilter");
        // 设置过滤器被调用的顺序
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public Filter signFilter() {
        return new SignFilter();
    }
}
