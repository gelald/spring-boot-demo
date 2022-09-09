package com.example.demo.controller;

import com.example.demo.controller.pretty.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author WuYingBin
 * date: 2022/9/9
 */
@SpringBootTest
public class TestServiceTest {

    private TestService testService;

    @Test
    public void test1() {
        this.testService.test(11);
    }

    @Autowired
    public void setTestService(TestService prettyTestService) {
        this.testService = prettyTestService;
    }
}
