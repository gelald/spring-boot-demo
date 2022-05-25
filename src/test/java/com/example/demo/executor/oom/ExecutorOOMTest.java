package com.example.demo.executor.oom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author WuYingBin
 * date: 2022/5/24
 */
@SpringBootTest
public class ExecutorOOMTest {

    @Test
    public void test1() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futureArrayList = new ArrayList<>();
        System.out.println("公司让你通知大家聚餐 你开车去接人");

        Future<String> future10 = executorService.submit(() -> {
            System.out.println("总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
            return "总裁上完大号了";

        });
        futureArrayList.add(future10);

        Future<String> future3 = executorService.submit(() -> {
            System.out.println("研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("研发：3分钟 我上完大号了。你来接吧");
            return "研发上完大号了";
        });
        futureArrayList.add(future3);

        Future<String> future6 = executorService.submit(() -> {
            System.out.println("中层管理：我在家上大号  要蹲10分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(6);
            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
            return "中层管理上完大号了";
        });
        futureArrayList.add(future6);

        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完了,等着接吧。");

        try {
            for (Future<String> future : futureArrayList) {
                String returnStr = future.get();
                System.out.println(returnStr + "，你去接他");
            }
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
