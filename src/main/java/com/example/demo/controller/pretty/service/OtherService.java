package com.example.demo.controller.pretty.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author WuYingBin
 * date: 2022/9/9
 */
@Service
@Validated
public class OtherService {

    public void method(@NotBlank String arg) {
        System.out.println("输入的参数是: " + arg);
    }
}
