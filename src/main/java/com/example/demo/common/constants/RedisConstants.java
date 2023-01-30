package com.example.demo.common.constants;

/**
 * @author qincq
 * @since 2022-2-10
 */
public class RedisConstants {

    /**
     * 1分钟
     */
    public static final long EXPIRE_TIME_1_MINUTES = 60;
    /**
     * 5分钟
     */
    public static final long EXPIRE_TIME_5_MINUTES = 300;
    /**
     * 10分钟
     */
    public static final long EXPIRE_TIME_10_MINUTES = 600;
    /**
     * 30分钟
     */
    public static final long EXPIRE_TIME_30_MINUTES = 1800;
    /**
     * 1小时
     */
    public static final long EXPIRE_TIME_1_HOUR = 3600;
    /**
     * 8小时
     */
    public static final long EXPIRE_TIME_8_HOUR = 28800;
    /**
     * 1天
     */
    public static final long EXPIRE_TIME_1_DAY = 86400;
    /**
     * 2天
     */
    public static final long EXPIRE_TIME_2_DAY = 172800;
    /**
     * 3天
     */
    public static final long EXPIRE_TIME_3_DAY = 259200;
    /**
     * 7天
     */
    public static final long EXPIRE_TIME_7_DAY = 604800;
    /**
     * 30天
     */
    public static final long EXPIRE_TIME_30_DAY = 2592000;

    /**
     * 权限key
     */
    public static final String CACHE_TYPE_API = "AUTH:PERMISSION:%s";


    /**
     * 账号标签关键词缓存key前缀
     */
    public static final String ACCOUNT_LABEL_KEYWORD_PREFIX = "A:LABEL:KEYWORD:%s";

    public static final String ACCOUNT_LABEL_KEYWORD_SIZE_PREFIX = "A:LABEL:KEYWORD:S:%s";

    public static final String HOT_NEWS_LABEL_ACCOUNT_COUNT_PREFIX = "HS:LABEL:COUNT:%s";

//    public static final String WEIBO_HOT_PREFIX = "WEIBO:HOT:%s";

//    public static final String WEIBO_HOT_USED_PREFIX = "WEIBO:HOT:USED:%s";

    public static final String CATEGORY_KEY_WORD_PREFIX = "CATEGORY:KEYWORD:%s:%s";

    public static final String CATEGORY_KEY_WORD_USED_PREFIX = "CATEGORY:KEYWORD:USED:%s:%s";


    /**
     * 飞书相关token缓存key前缀
     */
    public static final String FEISHU_REFRESH_TOKEN_PREFIX = "FS:REFRESH:%s";
    public static final String FEISHU_ACCESS_TOKEN_PREFIX = "FS:ACCESS:%s";
    public static final String FEISHU_TENANT_TOKEN_PREFIX = "FS:TENANT:%s";

}
