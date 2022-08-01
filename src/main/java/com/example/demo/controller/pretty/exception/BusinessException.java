package com.example.demo.controller.pretty.exception;

/**
 * @author WuYingBin
 * date: 2022/7/13
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
