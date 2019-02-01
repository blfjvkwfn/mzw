package com.mzw.lock.interceptor.lock;

import com.mzw.common.util.EmptyUtil;
import com.mzw.lock.anotation.lock.CacheLock;
import com.mzw.lock.anotation.lock.LockKey;
import com.mzw.lock.lock.IBaseLock;
import com.mzw.lock.lock.redis.Lock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * redis分布式锁处理类
 * @author mengzhaowei@boce.cn
 * @date 2019/1/28 14:03
 */
//@Aspect
//@Component
//@Order(2)
public class RedisLockInterceptor extends BaseLockInterceptor{
    private final StringRedisTemplate stringRedisTemplate;

    public RedisLockInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Around("execution(* com.mzw.lock.service..*.*(..)) && @annotation(com.mzw.lock.anotation.lock.CacheLock) ")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        return proceed(pjp);
    }

    @Override
    protected List<IBaseLock> getLocks(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock cacheLock = method.getAnnotation(CacheLock.class);

        List<String> keys = getKeys(method.getParameters(), pjp.getArgs());
        return getLocks(cacheLock.lockPrefix(), keys, cacheLock.timeout(), cacheLock.expireTime());
    }

    private Lock getLock(String key, long timeout, long expireTime) {
        return new Lock(key, timeout,expireTime ,stringRedisTemplate);
    }

    private List<IBaseLock> getLocks(String lockPrefix, List<String> keys, long timeout, long expireTime) {
        if (0 == keys.size() && !EmptyUtil.isFieldEmpty(lockPrefix)) {
            keys.add("");
        }
        List<IBaseLock> locks = new ArrayList<>(keys.size());
        keys.forEach(key -> locks.add(getLock(lockPrefix + key, timeout, expireTime)));
        return locks;
    }

    protected List<String> getKeys(Parameter[] parameters, Object[] args) {
        List<String> keys = new ArrayList<>(16);
        if (EmptyUtil.isObjEmpty(parameters) || EmptyUtil.isObjEmpty(args)
                || 0 == parameters.length || 0 == args.length) {
            return keys;
        }
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object arg = args[i];
            updateLockKey(parameter,arg, keys);
        }
        return keys;
    }


    private void updateLockKey(Parameter parameter, Object arg, List<String> keys) {
        LockKey lockKey = parameter.getAnnotation(LockKey.class);
        if (!EmptyUtil.isObjEmpty(lockKey) && !EmptyUtil.isObjEmpty(arg)) {
            keys.add(arg.toString());
        }
    }
}
