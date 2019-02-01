package com.mzw.lock.anotation.lock;

import java.lang.annotation.*;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/12/4 8:59
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
    /**
     * lock prefix
     * @return
     */
    String lockPrefix() default "";
    /**
     * timeout time default 20000ms
     * @return
     */
    long timeout() default 20000;
    /**
     * expitr time default 100000ms
     * @return
     */
    long expireTime() default 100000;

    /**
     * consul client地址
     * @return
     */
    String agentHost() default "";

    /**
     * 服务编号
     * @return
     */
    String serviceId() default "";
    //如果设置服务编号，则需要相应的客户端启动相应的service，只在服务器端启动相应的service不起作用
//    String serviceId() default "SYSTEM-DONOT-DEL";
}