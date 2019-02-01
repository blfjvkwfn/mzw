package com.mzw.lock.lock.redis;

import com.mzw.lock.lock.IBaseLock;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/1/28 14:03
 */
public class Lock implements IBaseLock {
    private final String key;
    private final long timeout;
    private final long expireTime;
    private final StringRedisTemplate stringRedisTemplate;

    public Lock(String key, long timeout, long expireTime, StringRedisTemplate stringRedisTemplate) {
        this.key = key;
        this.timeout = timeout;
        this.expireTime = expireTime;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean lock() {
        long nowTime = System.currentTimeMillis();
        final Random r = new Random();
        RedisConnection redisConnection = stringRedisTemplate.getConnectionFactory().getConnection();
        try {
            // 不断循环向Master节点请求锁，当请求时间(System.nanoTime() - nano)超过设定的超时时间则放弃请求锁
            while ((System.currentTimeMillis() - nowTime) < timeout) {
                // 将锁作为key存储到redis缓存中，存储成功则获得锁
                if (redisConnection.setNX(key.getBytes(), String.valueOf(System.currentTimeMillis()).getBytes())) {
                    // 设置锁的有效期，也是锁的自动释放时间，也是一个客户端在其他客户端能抢占锁之前可以执行任务的时间
                    // 可以防止因异常情况无法释放锁而造成死锁情况的发生
                    stringRedisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
                    // 上锁成功结束请求
                    return true;
                }
                // 防止上锁之后没有设置超时时间就挂了导致没有超时时间死锁
                if (0 > stringRedisTemplate.getExpire(key, TimeUnit.MILLISECONDS)) {
                    stringRedisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
                }
                // 获取锁失败时，应该在随机延时后进行重试，避免不同客户端同时重试导致谁都无法拿到锁的情况出现
                // 睡眠3毫秒后继续请求锁
                Thread.sleep(3, r.nextInt(500));
            }
        } catch (Exception e) {
            e.printStackTrace();
            unlock();
            throw new RuntimeException("locking error",e);
        } finally {
            redisConnection.close();
        }
        return false;
    }

    @Override
    public boolean unlock() {
        boolean success = false;
        try {
            success = stringRedisTemplate.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
