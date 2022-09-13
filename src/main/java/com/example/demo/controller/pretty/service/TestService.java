package com.example.demo.controller.pretty.service;

import com.example.demo.controller.pretty.dto.TestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author WuYingBin
 * date: 2022/7/13
 */
@Slf4j
@Service(value = "prettyTestService")
public class TestService {

    public void save(TestDTO testDTO) {
        log.info("保存成功: {}", testDTO);
    }
}
