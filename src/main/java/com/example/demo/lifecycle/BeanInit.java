package com.example.demo.lifecycle;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @author WuYingBin
 * date: 2022/12/26
 */
public class BeanInit implements InitializingBean {
    public BeanInit() {
        // 1
        System.out.println("BeanInit 构造方法执行");
    }

    @PostConstruct
    public void postConstruct() {
        // 2
        System.out.println("@PostConstruct 修饰的方法执行");
    }

    @Override
    public void afterPropertiesSet() {
        // 3
        System.out.println("afterPropertiesSet 方法执行");
    }

    public void initMethod() {
        // 4
        System.out.println("@Bean 注解的 initMethod 方法执行");
    }
}
