package com.example.demo.lambda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author WuYingBin
 * date: 2022/4/27
 */
@SpringBootTest
public class LambdaTests {
    private static void log(Printer printer) {
        printer.print();
    }

    @Test
    public void test1() {
        Integer i = 650;
        Double d = 85.8;
        log(() -> {
            System.out.println("lambda表达式外的值：" + i);
            System.out.println("lambda表达式外的值：" + d);
        });
    }

    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            log(() -> System.out.println("lambda表达式外的值：" + finalI));
        }
    }
}
