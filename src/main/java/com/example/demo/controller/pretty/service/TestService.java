package com.example.demo.controller.pretty.service;

import com.example.demo.controller.pretty.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author WuYingBin
 * date: 2022/7/13
 */
@Slf4j
@Service(value = "prettyTestService")
public class TestService {

    private OtherService otherService;

    public void save(TestDTO testDTO) {
        log.info("保存成功: {}", testDTO);
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
