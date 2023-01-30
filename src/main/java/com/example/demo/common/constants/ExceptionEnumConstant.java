package com.example.demo.common.constants;


import com.example.demo.exception.ServiceExceptionEnum;

/**
 * @author qincq
 * @since 2022-2-8
 */
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
    ROOT_GROUP_EDIT_ERROR(10306, "不能修改root组"),
    USER_GROUP_ADD_FAIL(10307, "无法将用户添加到不存在的组"),
    USER_PHONE_NOT_EMPTY(10308, "手机号码不能为空"),

    /**
     * 分组
     */
    GROUP_NOT_EXIT_ERROR(10404, "分组不存在"),
    GROUP_NAME_EXIT_ERROR(10405, "分组名已被使用，请重新填入新的分组名"),
    GROUP_TYPE_COMMON_ERROR(10406, "类型错误，请选择控评或者养号"),

    /**
     * 平台
     */
    PLATFORM_EXITS_ERROR(10501, "平台已存在"),
    PLATFORM_CANNOT_BE_EMPTY(10502, "平台不存在"),
    PLATFORM_OPERATE_EXITS_ERROR(10503, "操作已存在"),
    PLATFORM_BINDING_CANNOT_BE_DELETED(10504, "平台已绑定账号,无法删除"),
    SCRIPT_IS_PLATFORM_BOUND(10505, "脚本已绑定平台,无法删除"),
    /**
     * 文件
     */
    FILE_DOWNLOAD_ERROR(10601, "文件下载失败"),
    FILE_UPLOAD_ERROR(10602, "文件上传失败"),
    UPLOAD_FILE_NULL(10603, "文件为空"),
    READ_FILE_NOT_ERROR(10604, "读取文件失败"),
    DOWNLOAD_FILE_FAILED(10605, "下载失败"),
    EXCEL_FILE_ERROR(10606, "请上传Excel文件"),

    /**
     * 账号
     */
    ACCOUNT_EXITS_ERROR(10701, "账号已存在"),
    ACCOUNT_IMPORT_FAILED(10702, "账号信息导入失败"),
    ACCOUNT_NOT_FOUND(10702, "账号不存在"),
    ACCOUNT_NOT_UPDATE(10702, "暂无可更新的账号"),
    ACCOUNT_USE_ZERO(10703, "没有可用的账号"),

    /**
     * 脚本
     */
    SCRIPT_EXITS_ERROR(10801, "同平台脚本类型已存在"),
    SCRIPT_TYPE_PLATFORM_NOTNULL_ERROR(10802, "脚本类型和平台不能为空"),

    /**
     * 设备
     */
    DEVICE_EXITS_ERROR(10901, "设备已存在"),
    DEVICE_CANNOT_BE_EMPTY(10902, "设备不存在"),
    DEVICE_BINDING_CANNOT_BE_DELETED(10903, "设备已绑定账号,无法删除"),
    DEVICE_BINDING_USER(10904, "以下设备已绑定管理员："),


    /**
     * 标签
     */
    LABEL_EXITS_ERROR(11001, "标签已存在"),
    LABEL_NAME_NOTNULL_ERROR(11002, "标签名不能为空"),
    LABEL_BINDING_CANNOT_BE_DELETED(11003, "设备已绑定账号,无法删除"),
    LABEL_NOT_FOUND(11004, "标签不存在"),
    LABEL_LENGTH_ZERO(11005, "选择的列表为空"),
    LABEL_SELECT_ZERO(11006, "用途和角色至少选择一个"),
    CURRENT_LABEL_NOT_FOUND(11007, "请检查通用标签是否存在或者是否禁用!"),


    /**
     * 多厂商
     */
    SUPPLIER_DOES_NOT_INCLUDE(11101, "请选择正确的平台导入"),


    /**
     * 任务
     */
    TASK_NOT_DEVICE_ACCOUNT_ERROR(11201, "没有有效的设备或账号"),
    TASK_NOT_ENOUGH_ACCOUNT_ERROR(11202, "没有足够的账号"),
    TASK_NOT_ENOUGH_CONTENT_ERROR(11203, "内容库内容不足"),
    TASK_TYPE_ERROR(11204, "任务类型没有选择控评或者养号"),
    TASK_NOT_FOUND(11205, "未找到可执行的任务"),
    TASK_BEFORE_TIME(11206, "未到执行时间"),
    TASK_NOT_CONTENT_REPOSITORIES_ERROR(11205, "内容库不存在或已经被禁用"),
    TASK_COMMENTS_ARE_INCONSISTENT(11205, "评论自填数与填写数不一致"),
    IMPORT_TASK_REQUIRED_ITEM_CANNOT_BE_BLANK(11205, "导入任务信息必填项不能为空"),
    TASK_NOT_SUPPORT_OPERATE(11206, "不支持操作"),
    TASK_IMPORT_OPERATE_ERROR(11207, "具体操作填写错误"),
    TASK_CONTENT_REPOSITORIES_ERROR(11208, "没有选择内容库"),
    TASK_PARAM_FORMAT_ERROR(11209, "参数填写格式错误，请检查"),
    TASK_NUM_ZERO_ERROR(11210, "任务数不能为0"),
    NOT_CHOOSE_EMOJI(11211, "请检查评论库或表情库是否已选择"),
    NO_START_TASK(11212, "没有可以开始的任务"),
    NO_CHOOSE_LIVE_ID(11213, "请选择直播号"),
    LIVE_ROOM_EXITS(11214, "直播间已存在"),
    TASK_NAME_MISS(11215, "任务名称未填！"),
    PLATFORM_MISS(11216, "平台未填！"),
    DEVICE_TYPE_MISS(11217, "终端形式未填！"),
    TITLE_MISS(11218, "标题未填！"),
    URL_MISS(11219, "链接地址未填！"),
    CONTENT_MISS(11220, "评论内容未填！"),
    OPERATING_COUNT_MISS(11221, "操作次数未填！"),
    OPERATION_MISS(11222, "具体操作未填！"),
    LENGTH_TO_LONG(11223, "数据不可超过50万条,请重新筛选"),
    /**
     * 评论库
     */
    CONTENT_REPOSITORIES_IS_EMPTY(11301, "当前无临时评论库"),
    CONTENT_REPOSITORIES_IS_EXITS(11302, "评论库已存在,请换个名字重试"),
    TEMPORARY_COMMENT_LIBRARY_CREATION_FAIL(11303, "临时评论库创建失败"),
    IS_RECOMMENDED_TO_CHANGE_THE_LIBRARY(11304, "所选评论库有重名,建议换个库转正"),
    TEMPORARY_COMMENT_BANK_HAS_NO_COMMENTS(11305, "临时评论库无评论"),
    RETENTION_TIME_CANNOT_BE_EMPTY(11305, "保留时间不能为空"),

    /**
     * 规则
     */
    RULE_EXITS_ERROR(11401, "规则已存在"),
    RULE_NAME_NOTNULL_ERROR(11402, "规则名不能为空"),
    CANNOT_ALL_BE_ZERO(11403, "养号和控评不能都为空"),
    RULE_NOT_FOUNT(11404, "规则不存在"),

    /**
     * 舆情数据
     */
    SENTIMENT_DATA_ERROR(11501, "数据缺失必要值"),

    /**
     * 上传脚本
     */
    WITHOUT_THE_SCRIPT(11501, "没有该脚本"),
    DEVICE_ID_IS_INVALID(11501, "设备id不合法"),
    FILE_DOES_NOT_EXIST(11501, "文件不存在"),

    /**
     * 系统消息推送
     */
    EXPORTER_TEMPLATE_NO_EXIST_ERROR(11601, "模板不存在"),
    RECEIVER_NO_EXIST_ERROR(11602, "不存在接收人"),
    FAILED_TO_GET_FLY_BOOK_USER_ID(11603, "获取飞书用户ID失败"),
    /**
     * 主题
     */
    ACCOUNT_CONTENT_LABEL_NO_BIND_ERROR(11701, "主题未绑定账号标签或内容标签"),


    /**
     * rpc
     */
    RPC_ACTION_ERROR(11801, "调用远程接口错误,请联系管理员"),
    SPIDER_KEYWORD_ERROR(11802, "关键词爬取出错"),
    SPIDER_COMMENT_ERROR(11803, "评论爬取出错"),

    /**
     * 标签分类
     */
    CATEGORY_NAME_ALREADY_EXISTS(11901, "分类名称已存在"),

    /**
     * 设备架子
     */
    SHELF_NAME_EXITS(12001, "架子名称已存在"),

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
