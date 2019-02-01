package com.mzw.lock.interceptor.lock;

import com.mzw.lock.exception.LockException;
import com.mzw.lock.lock.IBaseLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/1/10 8:57
 */
@Slf4j(topic = "lock")
public class BaseLockInterceptor {

    protected Object proceed(ProceedingJoinPoint pjp) throws Throwable {
        List<IBaseLock> locks = getLocks(pjp);
        Object result = null;
        boolean isSuccess = true;
        try {
            // 获取分布式互斥锁
            for (IBaseLock lock : locks) {
                if (!lock.lock()) {
                    isSuccess = false;
                    break;
                }
            }

            if (isSuccess) {
                result = pjp.proceed();
            } else {
                log.warn("get lock failed, method:{}", pjp.getSignature().getName());
                throw new LockException("timeout");
            }
        } catch (Throwable e) {
            throw e;
        } finally {
            try {
                locks.forEach(lock -> lock.unlock());
            } catch (Exception e) {
                log.error("unlock error, message:{}", e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

    protected List<IBaseLock> getLocks(ProceedingJoinPoint pjp) {
        return new ArrayList<>(0);
    }
}
