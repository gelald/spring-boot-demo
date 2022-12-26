package com.example.demo.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WuYingBin
 * date: 2022/12/26
 */
@Configuration
public class BeanConfiguration {

    /*
    在Spring框架中，bean在加载、销毁时，都可以执行特定的方法，来实现某些功能的增强、信息的记录。
    加载时增强的方式：@PostConstruct、InitializingBean、@Bean的initMethod
    销毁时增强的方式：@PreDestroy、DisposingBean、@Bean的destroyMethod

    加载过程各方法的顺序
    1. 构造方法
    2. 被 @PostConstruct 注解修饰的方法
    3. InitializingBean 接口方法
    4. @Bean 中指定的 initMethod 方法

    销毁过程各方法的顺序
    1. 被 @PostDestroy 注解修饰的方法
    2. DisposingBean 接口方法
    3. @Bean 中指定的 destroyMethod 方法

    记忆：
    1. 类本身的行为 优先于 外部赋能行为
    2. Java注解 优先于 Spring接口 优先于 Spring注解
     */

    @Bean(initMethod = "initMethod")
    public BeanInit beanInit() {
        return new BeanInit();
    }

    @Bean(destroyMethod = "destroyMethod")
    public BeanDestroy beanDestroy() {
        return new BeanDestroy();
    }
}
