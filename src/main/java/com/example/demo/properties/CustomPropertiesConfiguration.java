package com.example.demo.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: WuYingBin
 * date: 2021/12/18
 */
@Configuration
@EnableConfigurationProperties(value = {OrderModuleProperties.class, PayModuleProperties.class})
public class CustomPropertiesConfiguration {
}
