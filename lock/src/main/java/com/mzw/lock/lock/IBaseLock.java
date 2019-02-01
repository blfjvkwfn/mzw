package com.mzw.lock.lock;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/1/10 10:31
 */
public interface IBaseLock {
    boolean lock();
    boolean unlock();
}
