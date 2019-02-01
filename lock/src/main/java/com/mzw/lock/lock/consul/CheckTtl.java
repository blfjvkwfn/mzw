package com.mzw.lock.lock.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.OperationException;
import com.ecwid.consul.v1.agent.model.NewCheck;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 该类用于consul分布式锁上锁后对上锁的key进行健康检查
 * 由于系统宕机或者重启等原因会导致checkttl没有被注销，影响本机所有服务，
 * 所以不再使用本类进行分布式锁处理，改为使用consul renew session的方式维护分布式锁
 * @author mengzhaowei@boce.cn
 * @date 2018/12/20 14:26
 */
@Slf4j(topic = "lock")
public class CheckTtl {

    private ConsulClient consulClient;

    @Getter
    private String checkId;
    private NewCheck check;
    private Timer timer;
    @Setter
    private String ttl = "30s";
    @Setter
    private String timeout = "10s";
    @Setter
    private String serviceId = "";

    private int ttlDelay = 5000;
    private int ttlPeriod = 10000;

    public CheckTtl(String checkId, ConsulClient consulClient) {
        log.info("checkId:" + checkId);
        this.checkId = checkId;
        this.consulClient = consulClient;
    }

    public void agentCheckRegister() {
        this.check = new NewCheck();
        check.setId(checkId);
        check.setName(checkId);
        check.setTtl(ttl);
        check.setServiceId(serviceId);
//        check.setInterval("10s");
        check.setTimeout(timeout);
        this.consulClient.agentCheckRegister(check);
        log.info("check ttl agentCheckRegister checkId:" + checkId);
    }

    public void agentCheckDeregister() {
        if (this.checkId != null) {
            log.info("check ttl agentCheckDeregister checkId:" + checkId);
            this.consulClient.agentCheckDeregister(checkId);
        }
    }


    public boolean isRunning() {
        if (this.timer == null) {
            return false;
        }
        return true;
    }

    public void start() {
        if (!isRunning()) {
            agentCheckRegister();
            consulClient.agentCheckPass(checkId);
            this.timer = new Timer();
            timer.scheduleAtFixedRate(new TtlTask(), ttlDelay, ttlPeriod);
        }
    }

    public void stop() {
        if (this.timer != null) {
            try {
                timer.cancel();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("timer cancel failed checkId:" + checkId);
            }
        }
        try {
            agentCheckDeregister();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("agent check deregister failed:checkId={},message={}",checkId, e.getMessage());
            agentCheckDeregister();
        }
    }

    class TtlTask extends TimerTask {
        @Override
        public void run() {
            try {
                log.info("agentCheckPass checkId:", checkId);
                consulClient.agentCheckPass(checkId);
            } catch (OperationException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
