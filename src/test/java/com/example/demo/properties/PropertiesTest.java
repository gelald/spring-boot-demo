package com.example.demo.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author WuYingBin
 * date: 2022/5/25
 */
@SpringBootTest
public class PropertiesTest {

    @Autowired
    private ReadPropertiesComponent readPropertiesComponent;

    @Test
    public void test1() {
        System.out.println(this.readPropertiesComponent.getList());
        System.out.println(this.readPropertiesComponent.getSet());
        System.out.println(this.readPropertiesComponent.getMap());
    }

    @Test
    public void test2() {
        this.readPropertiesComponent.test2();
    }

}
