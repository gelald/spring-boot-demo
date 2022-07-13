package com.example.demo.presentation.pretty.service;

import com.example.demo.presentation.pretty.dto.TestDTO;
import org.springframework.stereotype.Service;

/**
 * @author WuYingBin
 * date: 2022/7/13
 */
@Service(value = "prettyTestService")
public class TestService {

    public void save(TestDTO testDTO) {
        System.out.println("保存成功");
    }
}
