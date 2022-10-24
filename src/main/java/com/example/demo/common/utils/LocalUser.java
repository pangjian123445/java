package com.example.demo.common.utils;


import java.util.HashMap;
import java.util.Map;

/**
 * 线程安全的当前登录用户，如果用户未登录，则得到 null
 *
 */
public class LocalUser {

    private static ThreadLocal<Map<String, Object>> local = new ThreadLocal<>();


//    /**
//     * 设置登录用户
//     *
//     * @param user user
//     */
//    public static void setLocalUser(UserDto user) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("user", user);
//        local.set(map);
//    }
//
//
//    public static UserDto getUser() {
//        Map<String, Object> map = local.get();
//        return (UserDto) map.get("user");
//    }
//
//    public static <T> T getLocalUser(Class<T> clazz) {
//        return (T) local.get();
//    }
//
//    /**
//     * 清理当前用户
//     */
//    public static void clearLocalUser() {
//        local.remove();
//    }
//
//    public static String getUsername() {
//        return getUser().getUsername();
//    }

}
