package com.mzw.lock.exception;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/1/31 16:42
 */
public class LockException extends RuntimeException{
    private String code;

    public LockException(String code) {
        this.code = code;
    }
}
