package com.example.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: WuYingBin
 * date: 2021/12/18
 */
@Data
@ConfigurationProperties(prefix = "biz.order")
public class OrderModuleProperties {
}
