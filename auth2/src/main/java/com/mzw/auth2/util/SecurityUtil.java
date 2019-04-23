package com.mzw.auth2.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Jonathan Meng
 * @date 03/04/2019
 */
public class SecurityUtil {
    public static Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
