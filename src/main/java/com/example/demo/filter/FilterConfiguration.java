package com.example.demo.filter;

import com.example.demo.filter.two.FilterTwo;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WuYingBin
 * date: 2023/4/4
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean<FilterTwo> filterRegistrationBean() {
        FilterTwo filterTwo = new FilterTwo();
        FilterRegistrationBean<FilterTwo> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(filterTwo);
        //设置过滤器名、过滤规则
        filterRegistrationBean.setName("filter-two");
        filterRegistrationBean.addUrlPatterns("/bad/*");
        return filterRegistrationBean;
    }

    @Bean
    public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean() {
        DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean = new DelegatingFilterProxyRegistrationBean("filterThree");
        delegatingFilterProxyRegistrationBean.setName("filter-three");
        delegatingFilterProxyRegistrationBean.addUrlPatterns("/bad/*");
        return delegatingFilterProxyRegistrationBean;
    }
}
