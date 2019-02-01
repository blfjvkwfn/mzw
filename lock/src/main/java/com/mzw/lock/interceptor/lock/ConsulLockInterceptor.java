package com.mzw.lock.interceptor.lock;

import com.ecwid.consul.v1.ConsulClient;
import com.mzw.common.util.EmptyUtil;
import com.mzw.lock.anotation.lock.CacheLock;
import com.mzw.lock.anotation.lock.LockKey;
import com.mzw.lock.lock.IBaseLock;
import com.mzw.lock.lock.consul.CheckTtl;
import com.mzw.lock.lock.consul.Lock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * consul分布式锁处理类
 * @author mengzhaowei@boce.cn
 * @date 2018/12/4 8:49
 */
@Aspect
@Component
@Order(2)
public class ConsulLockInterceptor extends BaseLockInterceptor{
    @Value("${spring.cloud.consul.host}")
    protected String configAgentHost = Lock.DEFAULT_AGENT_HOST;
    // consul session 超时时间
    @Value("${boce.consul.lock.session.ttl}")
    protected String sessionTTL;

    // consul renew session定时任务第一次执行延迟时间
    @Value("${boce.consul.lock.session.timer.delay}")
    protected Long sessionTimerDelay;

    // consul renew session定时任务每次执行时间间隔
    @Value("${boce.consul.lock.session.timer.period}")
    protected Long sessionTimerPeriod;


    @Around(value = "execution(* com.mzw.lock.service..*.*(..)) && @annotation(CacheLock) ")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        return proceed(pjp);
    }

    @Override
    protected List<IBaseLock> getLocks(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock cacheLock = method.getAnnotation(CacheLock.class);

        List<String> keys = getKeys(method.getParameters(), pjp.getArgs());
        return getLocks(cacheLock.lockPrefix(), keys, cacheLock.timeout(), cacheLock.agentHost(), cacheLock.serviceId());
    }

    private IBaseLock getLock(String key, long timeout, String agentHost, String serviceId) {
        if (EmptyUtil.isFieldEmpty(agentHost)) {
            agentHost = this.configAgentHost;
        }
        ConsulClient consulClient = new ConsulClient(agentHost);
//        CheckTtl checkTtl = getCheckTtl(key, serviceId, consulClient);

        // 不再使用checkttl维护分布式锁，使用renew session维护分布式锁
        Lock lock = new Lock(consulClient, key, null, timeout);
        lock.setSessionTTL(EmptyUtil.isFieldEmpty(sessionTTL) ? lock.getSessionTTL() : sessionTTL);
        lock.setSessionTimerDelay(EmptyUtil.isObjEmpty(sessionTimerDelay) ? lock.getSessionTimerDelay() : sessionTimerDelay);
        lock.setSessionTimerPeriod(EmptyUtil.isObjEmpty(sessionTimerPeriod) ? lock.getSessionTimerPeriod() : sessionTimerPeriod);
        return lock;
    }

    private List<IBaseLock> getLocks(String lockPrefix, List<String> keys, long timeout, String agentHost, String serviceId) {
        if (0 == keys.size() && !EmptyUtil.isFieldEmpty(lockPrefix)) {
            keys.add("");
        }
        List<IBaseLock> locks = new ArrayList<>(keys.size());
        keys.forEach(key -> locks.add(getLock(lockPrefix + key, timeout, agentHost, serviceId)));
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

    private CheckTtl getCheckTtl(String key, String serviceId, ConsulClient consulClient) {
        CheckTtl ttl = new CheckTtl(key, consulClient);
        ttl.setServiceId(serviceId);
        return ttl;
    }
}
