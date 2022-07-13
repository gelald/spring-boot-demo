package com.example.demo.presentation.pretty.exception;

/**
 * @author WuYingBin
 * date: 2022/7/13
 */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
