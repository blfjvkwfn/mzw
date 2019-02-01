package com.mzw.lock.util;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/1/28 10:04
 */
public class UserUtil {
    private static ThreadLocal<String> userIds = new ThreadLocal();
    public static String getUserId() {
        return userIds.get();
    }
    public static void addUserId(String userId) {
        userIds.set(userId);
    }

    public static void remove() {
        userIds.remove();
    }
}
