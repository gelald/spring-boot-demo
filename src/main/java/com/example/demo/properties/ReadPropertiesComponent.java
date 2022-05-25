package com.example.demo.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: WuYingBin
 * date: 2021/12/17
 */
@Data
@Component
@ConfigurationProperties(prefix = "properties.collection")
public class ReadPropertiesComponent {
    private List<String> list;
    private Set<Integer> set;
    private Map<String, Object> map;

    @Value("#{'${value.collection.list:}'.empty ? null : '${test2.collection.list:}'.split(',')}")
    private List<String> testList;
    @Value("#{'${value.collection.set:}'.empty ? null : '${test2.collection.set:}'.split(',')}")
    private Set<String> setList;

    public void test2() {
        System.out.println(this.testList);
        System.out.println(this.setList);
    }

}
