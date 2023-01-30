package com.example.demo.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author qincq
 * @since 2022-2-8
 */
@ToString
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -2543143155682837858L;

    private int code;
    private String message;
    private int errorCode;
    private T data;

    public Response setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Response setCode(ResponseCodeEnum responseCode) {
        this.code = responseCode.code();
        return this;
    }

    public Response setCode(int code) {
        this.code = code;
        return this;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Response setData(T data) {
        this.data = data;
        return this;
    }

}
