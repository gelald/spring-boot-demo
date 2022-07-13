package com.example.demo.executor.update;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.concurrent.*;

/**
 * @author WuYingBin
 * date: 2022/5/24
 */
@SpringBootTest
public class ExecutorUpdateTest {
    private final StopWatch stopWatch = new StopWatch();

    @BeforeEach
    public void before() {
        this.stopWatch.start();
    }

    @AfterEach
    public void after() {
        this.stopWatch.stop();
        System.out.printf("方法执行时间: %d毫秒.%n", this.stopWatch.getTotalTimeMillis());
    }

    @Test
    public void test1() throws InterruptedException {
        // 查询订单信息
        System.out.println("===== 正在查询订单信息 =====");
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("***** 查询订单信息成功 *****");
        // 查询司机信息
        System.out.println("===== 正在查询司机信息 =====");
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("***** 查询司机信息成功 *****");
        // 查询车辆信息
        System.out.println("===== 正在查询车辆信息 =====");
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("***** 查询车辆信息成功 *****");
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        // 定义查询订单信息任务
        Callable<String> orderCallable = () -> {
            System.out.println("===== 正在查询订单信息 =====");
            TimeUnit.MILLISECONDS.sleep(200);
            return "***** 查询订单信息成功 *****";
        };
        FutureTask<String> orderFuture = new FutureTask<>(orderCallable);
        // 定义查询司机信息任务
        Callable<String> driverCallable = () -> {
            System.out.println("===== 正在查询司机信息 =====");
            TimeUnit.MILLISECONDS.sleep(100);
            return "***** 查询司机信息成功 *****";
        };
        FutureTask<String> driverFuture = new FutureTask<>(driverCallable);
        // 定义查询车辆信息任务
        Callable<String> carCallable = () -> {
            System.out.println("===== 正在查询车辆信息 =====");
            TimeUnit.MILLISECONDS.sleep(200);
            return "***** 查询车辆信息成功 *****";
        };
        FutureTask<String> carFuture = new FutureTask<>(carCallable);
        // 启动查询订单信息任务
        new Thread(orderFuture).start();
        // 启动查询司机信息任务
        new Thread(driverFuture).start();
        // 启动查询车辆信息任务
        new Thread(carFuture).start();
        // 获取订单结果
        System.out.println(orderFuture.get());
        // 获取司机结果
        System.out.println(driverFuture.get());
        // 获取车辆结果
        System.out.println(carFuture.get());
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        // 定义线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 定义查询订单信息任务
        Callable<String> orderCallable = () -> {
            System.out.println("===== 正在查询订单信息 =====");
            TimeUnit.MILLISECONDS.sleep(200);
            return "***** 查询订单信息成功 *****";
        };
        // 定义查询司机信息任务
        Callable<String> driverCallable = () -> {
            System.out.println("===== 正在查询司机信息 =====");
            TimeUnit.MILLISECONDS.sleep(100);
            return "***** 查询司机信息成功 *****";
        };
        // 定义查询车辆信息任务
        Callable<String> carCallable = () -> {
            System.out.println("===== 正在查询车辆信息 =====");
            TimeUnit.MILLISECONDS.sleep(200);
            return "***** 查询车辆信息成功 *****";
        };
        // 提交查询订单信息任务
        Future<String> orderFuture = executorService.submit(orderCallable);
        // 提交查询司机信息任务
        Future<String> driverFuture = executorService.submit(driverCallable);
        // 提交查询车辆信息任务
        Future<String> carFuture = executorService.submit(carCallable);
        // 获取订单结果
        System.out.println(orderFuture.get());
        // 获取司机结果
        System.out.println(driverFuture.get());
        // 获取车辆结果
        System.out.println(carFuture.get());
        // 销毁线程池
        executorService.shutdown();
    }

    @Test
    public void test4() throws InterruptedException, ExecutionException {
        // 创建固定线程的线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // 创建CompletionService
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        // 定义查询订单信息任务
        Callable<String> orderCallableTask = () -> {
            System.out.println("===== 正在查询订单信息 =====");
            TimeUnit.MILLISECONDS.sleep(200);
            return "***** 查询订单信息成功 *****";
        };
        // 定义查询司机信息任务
        Callable<String> driverCallableTask = () -> {
            System.out.println("===== 正在查询司机信息 =====");
            TimeUnit.MILLISECONDS.sleep(100);
            return "***** 查询司机信息成功 *****";
        };
        // 定义查询车辆信息任务
        Callable<String> carCallableTask = () -> {
            System.out.println("===== 正在查询车辆信息 =====");
            TimeUnit.MILLISECONDS.sleep(200);
            return "***** 查询车辆信息成功 *****";
        };

        // 提交查询订单信息任务
        completionService.submit(orderCallableTask);
        // 提交查询司机信息任务
        completionService.submit(driverCallableTask);
        // 提交查询车辆信息任务
        completionService.submit(carCallableTask);

        for (int i = 0; i < 3; i++) {
            // 等待1秒内如果获取不了数据则超时
            Future<String> future = completionService.poll(1, TimeUnit.SECONDS);
            // 获取结果
            String result = future.get();
            System.out.println(result);
        }
        // 销毁线程池
        executor.shutdown();
    }

    @Test
    public void test5() {
        // 创建固定线程的线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // 定义、提交查询订单的任务，并指定执行完成的回调方法
        CompletableFuture<String> orderCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("===== 正在查询订单信息 =====");
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "***** 查询订单信息成功 *****";
        }, executor).whenComplete((s, throwable) -> System.out.println(s));

        // 定义、提交查询司机的任务，并指定执行完成的回调方法
        CompletableFuture<String> driverCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("===== 正在查询司机信息 =====");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "***** 查询司机信息成功 *****";
        }, executor).whenComplete((s, throwable) -> System.out.println(s));

        // 定义、提交查询车辆的任务，并指定执行完成的回调方法
        CompletableFuture<String> carCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("===== 正在查询车辆信息 =====");
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "***** 查询车辆信息成功 *****";
        }, executor).whenComplete((s, throwable) -> System.out.println(s));

        CompletableFuture.allOf(orderCompletableFuture, driverCompletableFuture, carCompletableFuture).join();
        // 销毁线程池
        executor.shutdown();
    }
}
