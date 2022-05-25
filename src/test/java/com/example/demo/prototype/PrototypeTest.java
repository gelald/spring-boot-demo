package com.example.demo.prototype;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author WuYingBin
 * date: 2022/5/25
 */
@SpringBootTest
public class PrototypeTest {

    @Test
    public void test1() {
        ConcretePrototype concretePrototype = ConcretePrototype.getInstance();
        ConcretePrototype clone = concretePrototype.clone();
        System.out.println(concretePrototype == clone);
    }

}
