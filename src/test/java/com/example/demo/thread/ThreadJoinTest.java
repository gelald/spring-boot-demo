package com.example.demo.thread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @author WuYingBin
 * date: 2022/6/3
 */
@SpringBootTest
public class ThreadJoinTest {
    @Test
    public void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("子线程正在执行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("子线程执行完毕");
        });
        thread.start();
        // 表示当前线程等待thread执行完再退出
        // 必须在thread.start后执行，否则没有效果
        thread.join();
        System.out.println("主线程等待子线程执行完毕后退出");
    }

    @Test
    public void test2() {
        Thread thread = new Thread(() -> {
            System.out.println("子线程正在执行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("子线程执行完毕");
        });
        thread.start();

        System.out.println("主线程不等待子线程执行完毕后退出");
    }
}
