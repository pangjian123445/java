package com.example.demo.common.constants;


import com.example.demo.exception.ServiceExceptionEnum;

public enum ExceptionEnumConstant implements ServiceExceptionEnum {
    /**
     * 统一异常
     */
    UNAUTHORIZED_ERROR(401, "未登录"),
    TOKEN_INVALID(402, "TOKEN已失效"),
    AUTHORIZATION_ERROR(403, "未授权"),
    RUNTIME_EXCEPTION_ERROR(500, "未知异常"),
    VERIFICATION_CODE_ERROR(405, "验证码错误"),
    LOGIN_FAILURE_OTHER_ERROR(601, "登录失败"),

    /**
     * 数据库操作相关
     */
    DB_INSERT_ERROR(10001, "数据插入失败"),
    DB_UPDATE_ERROR(10002, "数据更新失败"),
    DB_DELETE_ERROR(10003, " 数据删除失败"),
    DB_DUPLICATE_KEY_ERROR(10004, "存在数据重复"),
    DB_DATA_NOT_EXIST_ERROR(10005, "数据不存在"),

    /**
     * 参数异常
     */
    OBJECT_MAPPER_ERROR(10101, "系统异常,参数序列化错误"),
    REQUEST_PARAM_ERROR(10102, "提交参数异常"),
    REQUEST_PARAM_IS_NULL(10103, "提交参数为空"),
    REQUEST_PARAM_ILLEGAL_ERROR(10106, "参数存在非法内容"),
    VERIFICATION_CODE_EMPTY(10107, "验证码不能为空"),
    VERIFICATION_LENGTH_EMPTY(10108, "数据条数为0"),

    /**
     * COMMON
     */
    DELETE_COMMON_ERROR(10201, "删除失败"),
    ADD_COMMON_ERROR(10202, "添加失败"),
    UPDATE_COMMON_ERROR(10203, "更新失败"),
    EMAIL_USED_ERROR(10204, "邮箱已被使用，请重新填入新的邮箱"),
    NOT_COMMOND_ERROR(10205, "没有此命令"),

    /**
     * 用户
     */
    USER_EXIT_ERROR(10301, "已经有用户使用了该名称，请重新输入新的用户名"),
    USER_NOT_EXIT_ERROR(10302, "用户不存在"),
    USER_NAME_PASSWORD_ERROR(10303, "用户名或密码错误"),
    USER_ADD_ROOT_GROUP_ERROR(10304, "root分组不可添加用户"),
    USER_PASSWORD_INVALID(10304, "请输入正确的密码"),
    USER_CHANGE_PASSWORD_FAIL(10305, "密码修改失败"),
    USER_GROUP_ADD_FAIL(10307, "无法将用户添加到不存在的组"),
    USER_PHONE_NOT_EMPTY(10308, "手机号码不能为空"),
    USER_PASSWORD_ILLEGAL(10309, "密码过于简单"),
    USER_FORBID_LOGIN(10310,"密码过于简单，已禁止登录，请联系管理员修改密码"),

    /**
     * 文件
     */
    FILE_DOWNLOAD_ERROR(10601, "文件下载失败"),
    FILE_UPLOAD_ERROR(10602, "文件上传失败"),
    UPLOAD_FILE_NULL(10603, "文件为空"),
    READ_FILE_NOT_ERROR(10604, "读取文件失败"),
    DOWNLOAD_FILE_FAILED(10605, "下载失败"),
    EXCEL_FILE_ERROR(10606, "请上传Excel文件"),

    ;

    ExceptionEnumConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
