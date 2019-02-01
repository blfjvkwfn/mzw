package com.mzw.lock.anotation.lock;

import java.lang.annotation.*;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/1/10 8:55
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderKey {
    /**是否锁商品*/
    boolean isLockProduct() default false;
}
