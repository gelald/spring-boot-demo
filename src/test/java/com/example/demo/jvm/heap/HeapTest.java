package com.example.demo.jvm.heap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author WuYingBin
 * date: 2022/3/21
 */
@SpringBootTest
public class HeapTest {
    @Test
    public void testParam() {
        //返回 JVM 堆大小
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        //返回 JVM 堆的最大内存
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms : " + initialMemory + "M");
        System.out.println("-Xmx : " + maxMemory + "M");

        System.out.println("系统内存大小：" + initialMemory * 64 / 1024 + "G");
        System.out.println("系统内存大小：" + maxMemory * 4 / 1024 + "G");
    }
}
