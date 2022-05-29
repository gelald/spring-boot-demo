package com.example.demo.executor.shutdown;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author WuYingBin
 * date: 2022/5/28
 */
@SpringBootTest
public class ShutDownExecutorTest {

    private final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            10,
            20,
            0L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(30)
    );

    private void executeBiz(int index) {
        long sleepTime = new Double(Math.random() * 10000).longValue();
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
            System.out.println("当前线程执行结束: " + index);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeBiz(int index, CountDownLatch latch) {
        long sleepTime = new Double(Math.random() * 10000).longValue();
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
            System.out.println("当前线程执行结束: " + index);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    @Test
    public void test1() throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            final int index = i;
            poolExecutor.execute(() -> {
                System.out.println("当前线程任务开始执行: " + index);
                executeBiz(index);
            });
        }
        poolExecutor.shutdown();
        while (!poolExecutor.isTerminated()) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("线程池还没完成所有任务...");
        }
        System.out.println("全部线程完成执行，线程池成功停止");
    }

    @Test
    public void test2() throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            final int index = i;
            poolExecutor.execute(() -> {
                System.out.println("当前线程任务开始执行: " + index);
                executeBiz(index);
            });
        }
        while (poolExecutor.getTaskCount() != poolExecutor.getCompletedTaskCount()) {
            System.out.println("当前已提交的任务数: " + poolExecutor.getTaskCount() + ", 当前已完成的任务数: " + poolExecutor.getCompletedTaskCount());
            TimeUnit.SECONDS.sleep(1);
            System.out.println("线程池还没完成所有任务...");
        }
        System.out.println("全部线程完成执行，线程池成功停止");
    }

    @Test
    public void test3() throws InterruptedException {
        final int taskCount = 30;
        CountDownLatch countDownLatch = new CountDownLatch(taskCount);
        for (int i = 0; i < taskCount; i++) {
            final int index = i;
            poolExecutor.execute(() -> {
                System.out.println("当前线程任务开始执行: " + index);
                executeBiz(index, countDownLatch);
            });
        }
        System.out.println("正在等待线程池中的任务执行...");
        countDownLatch.await();
        System.out.println("全部线程完成执行，线程池成功停止");
    }
}
