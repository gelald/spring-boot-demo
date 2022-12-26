package com.example.demo.lifecycle;

import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

/**
 * @author WuYingBin
 * date: 2022/12/26
 */
public class BeanDestroy implements DisposableBean {

    @PreDestroy
    public void preDestroy() {
        // 1
        System.out.println("@PreDestroy 修饰的方法执行");
    }

    @Override
    public void destroy() {
        // 2
        System.out.println("destroy 方法执行");
    }

    public void destroyMethod() {
        // 3
        System.out.println("@Bean 注解的 destroyMethod 方法执行");
    }
}
