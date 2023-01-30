package com.example.demo.common.response;

import com.example.demo.exception.ServiceExceptionEnum;
import com.github.pagehelper.PageInfo;

/**
 * @author qincq
 * @since 2022-2-8
 */
public class ResponseGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Response genSuccessResult() {
        return new Response()
                .setCode(ResponseCodeEnum.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Response<T> genSuccessResult(T data) {
        return new Response()
                .setCode(ResponseCodeEnum.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }


    public static Response genFailResult(int errorCode,String message) {
        return new Response()
                .setCode(ResponseCodeEnum.FAIL)
                .setErrorCode(errorCode)
                .setMessage(message);
    }

    public static Response genFailResult(ServiceExceptionEnum exceptionEnum) {
        return new Response()
                .setCode(ResponseCodeEnum.FAIL)
                .setErrorCode(exceptionEnum.getCode())
                .setMessage(exceptionEnum.getMessage());
    }


    public static <T> Response<T> genFailResult(int errorCode,String message, T data) {
        return new Response()
                .setCode(ResponseCodeEnum.FAIL)
                .setMessage(message)
                .setErrorCode(errorCode)
                .setData(data);
    }

    public static Response genFailResult(int code, int errorCode, String message) {
        return new Response()
                .setCode(code)
                .setErrorCode(errorCode)
                .setMessage(message);
    }

    public static Response genSuccessPageResult(PageInfo pageInfo) {
        return genSuccessResult(new PageResponse().setPageInfo(pageInfo));
    }
}
