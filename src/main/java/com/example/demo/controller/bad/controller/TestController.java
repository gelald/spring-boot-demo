package com.example.demo.controller.bad.controller;

import com.example.demo.controller.bad.dto.TestDTO;
import com.example.demo.controller.bad.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }
}
