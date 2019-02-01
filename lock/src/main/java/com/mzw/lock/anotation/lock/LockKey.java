package com.mzw.lock.anotation.lock;

import java.lang.annotation.*;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/12/20 16:46
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockKey {
    String key() default "";
}
