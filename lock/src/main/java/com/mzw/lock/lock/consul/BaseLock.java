package com.mzw.lock.lock.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.session.model.NewSession;
import com.ecwid.consul.v1.session.model.Session;
import com.mzw.common.util.EmptyUtil;
import com.mzw.lock.util.UserUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author mengzhaowei@boce.cn
 * @date 2018/12/20 14:26
 */
@Data
@Slf4j(topic = "lock")
public class BaseLock {

    protected ConsulClient consulClient;
    protected String sessionId = null;
    protected String keyPath;   // 互斥锁、信号量存储在consul中的基础key路径
    protected CheckTtl checkTtl;  // Check Ttl
    private Timer timer;
    // consul session 超时时间
    private String sessionTTL = "10s";
    // consul renew session定时任务第一次执行延迟时间
    private long sessionTimerDelay = 5000L;
    // consul renew session定时任务每次执行时间间隔
    private long sessionTimerPeriod = 5000L;

    /**
     * @param consulClient
     * @param lockKey       同步锁在consul的KV存储中的Key路径，会自动增加prefix前缀，方便归类查询
     * @param checkTtl      对锁Session的TTL
     */
    protected BaseLock(ConsulClient consulClient, String lockKey, CheckTtl checkTtl) {
        this.consulClient = consulClient;
        this.keyPath = lockKey;
        this.checkTtl = checkTtl;
    }

    /**
     * 创建session
     * @param sessionName
     * @return
     */
    protected void createSession(String sessionName) {
        NewSession newSession = new NewSession();
        newSession.setName(sessionName);
//        if(checkTtl != null) {
//            checkTtl.start();
//            // 如果有CheckTtl，就为该Session设置Check相关信息
//            List<String> checks = new ArrayList<>();
//            checks.add(checkTtl.getCheckId());
//            newSession.setChecks(checks);
//            newSession.setBehavior(Session.Behavior.DELETE);
//            /** newSession.setTtl("60s");
//             指定秒数（10s到86400s之间）。如果提供，在TTL到期之前没有更新，则会话无效。
//             应使用最低的实际TTL来保持管理会话的数量。
//             当锁被强制过期时，例如在领导选举期间，会话可能无法获得最多双倍TTL，
//             因此应避免长TTL值（> 1小时）。**/
//        }
        newSession.setBehavior(Session.Behavior.DELETE);
        newSession.setTtl(sessionTTL);
        this.sessionId = consulClient.sessionCreate(newSession, null).getValue();
        timer = new Timer();
        String userId = UserUtil.getUserId();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                log.info("renew session, key path={},userId={}", keyPath, userId);
                consulClient.renewSession(sessionId, null);
            }
        } , sessionTimerDelay, sessionTimerPeriod);
    }

    /**
     * 根据成员变量sessionId来销毁session
     */
    protected void destroySession() {
        if (sessionId != null) {
            cancelTimer();
            consulClient.sessionDestroy(sessionId, null);
            sessionId = null;
        }
    }

    private void cancelTimer() {
        if (EmptyUtil.isObjEmpty(timer)) {
            return;
        }

        try {
            timer.cancel();
        } catch (Exception e) {
            log.error("cancel timer failed, keyPath={}, message={}", keyPath, e.getMessage());
            e.printStackTrace();
        }
    }
}
