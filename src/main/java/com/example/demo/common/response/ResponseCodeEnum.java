package com.example.demo.common.response;

/**
 * @author qincq
 * @since 2022-2-8
 */
public enum  ResponseCodeEnum {
    /**
     * 成功
     */
    SUCCESS(1),
    /**
     * 失败
     */
    FAIL(0);
    private final int code;

    ResponseCodeEnum(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
