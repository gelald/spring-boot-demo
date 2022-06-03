package com.example.demo.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author WuYingBin
 * date: 2022/6/1
 */
public class ThreadDaemonTest {

    public static void main(String[] args) {
        System.out.println("主线程启动");
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 进入等待时间");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 结束");
        });
        thread.setName("子线程");
        // 设置为true代表这个子线程是守护线程
        // 当程序中所有用户线程执行完，程序就会退出
        // thread.setDaemon(true);
        thread.start();
        System.out.println("主线程 进入等待时间");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("主线程 结束");
    }
}
