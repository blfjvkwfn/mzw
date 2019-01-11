package com.mzw.util;

import com.mzw.auth.UserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/1/3 13:53
 */
public class UserUtil {
    /**
     * 获取当前用户信息
     * @return
     */
    public static UserDetail getCurrentUser() {
        return (UserDetail) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     * @return
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext()
                .getAuthentication();
    }

    /**
     * 获取当前用户编号
     * @return
     */
    public static String getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    /**
     * 获取当前用户用户名
     * @return
     */
    public static String getCurrentUsername() {
        return getCurrentUser().getUsername();
    }

    /**
     * 是否通过验证
     * @return
     */
    public static boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }
}
