package com.example.demo.controller.pretty.service;

import com.example.demo.controller.pretty.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WuYingBin
 * date: 2022/7/13
 */
@Service(value = "prettyTestService")
public class TestService {

    private OtherService otherService;

    public void save(TestDTO testDTO) {
        System.out.println("保存成功");
    }

    public void test(Integer num) {
        if (num % 2 == 0) {
            this.otherService.method(num.toString());
        } else {
            this.otherService.method(null);
        }
    }

    @Autowired
    public void setOtherService(OtherService otherService) {
        this.otherService = otherService;
    }
}
