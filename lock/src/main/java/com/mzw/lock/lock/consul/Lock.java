package com.mzw.lock.lock.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.kv.model.PutParams;
import com.mzw.lock.lock.IBaseLock;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * 基于Consul的互斥锁
 *
 * @author mengzhaowei@boce.cn
 * @date 2018/12/20 14:26
 */
@Slf4j(topic = "lock")
public class Lock extends BaseLock implements IBaseLock {
    /**同步锁参数前缀*/
    private static final String prefix = "lock/";
    /**超时时间*/
    private final long timeout;
    public static final String DEFAULT_AGENT_HOST = "localhost";
    public static final String DEFAULT_SERVICE_ID = "";

    /**
     * @param consulClient
     * @param lockKey 同步锁在consul的KV存储中的Key路径，会自动增加prefix前缀，方便归类查询
     * @param checkTtl 对锁Session的TTL
     * @param timeout 超时时间
     */
    public Lock(ConsulClient consulClient, String lockKey, CheckTtl checkTtl, long timeout) {
        super(consulClient, prefix + lockKey, checkTtl);
        this.timeout = timeout;
    }

    /**
     * 获取同步锁
     *
     * @return
     */
    @Override
    public boolean lock() {
        boolean isSuccess = false;
        try {
            if (sessionId != null) {
                throw new RuntimeException(sessionId + " - Already locked!");
            }
            createSession("lock-" + this.keyPath);
            long nowTime = System.currentTimeMillis();
            final Random r = new Random();
            while ((System.currentTimeMillis() - nowTime) < timeout) {
                PutParams putParams = new PutParams();
                putParams.setAcquireSession(sessionId);
                if(consulClient.setKVValue(keyPath, "lock:" + LocalDateTime.now(), putParams).getValue()) {
                    isSuccess = true;
                    break;
                }
                // 睡眠50毫秒后继续请求锁
                Thread.sleep(50);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return isSuccess;
    }

    /**
     * 释放同步锁
     *
     * @return
     */
    @Override
    public boolean unlock() {
        boolean success = false;
        try {
            success = executeUnlock();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return success;
    }

    private boolean executeUnlock() {
        // 不再使用checkTtl类进行分布式锁健康检查，改为使用consul renew session的方式维护分布式锁
//        if(checkTtl != null) {
//            checkTtl.stop();
//            PutParams putParams = new PutParams();
//            putParams.setReleaseSession(sessionId);
//            consulClient.setKVValue(keyPath, "unlock:" + LocalDateTime.now(), putParams).getValue();
//        }

        destroySession();
        return true;
    }
}
