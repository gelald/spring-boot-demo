package com.example.demo.controller.bad.controller;

import com.example.demo.controller.bad.dto.TestDTO;
import com.example.demo.controller.bad.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author WuYingBin
 * date: 2022/7/13
 */
@RestController
@RequestMapping("/bad")
public class TestController {

    private TestService testService;

    @PostMapping("/test")
    public Double test(@RequestBody TestDTO testDTO) {
        try {
            Double result = this.testService.service(testDTO);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/test")
    public String test() {
        return "test 接口不做拦截";
    }

    @GetMapping("/a1")
    public String a1() {
        return "hello a1，这个接口做了拦截";
    }

    @GetMapping("/a2")
    public String a2() {
        return "hello a2，这个接口做了拦截";
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }
}
