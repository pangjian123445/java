package com.example.demo.exception;


import java.io.Serializable;

public class SQLInjectionException extends RuntimeException implements Serializable {

    public SQLInjectionException() {
        super("参数异常");
    }

    public SQLInjectionException(String message) {
        super(message);
    }
}
