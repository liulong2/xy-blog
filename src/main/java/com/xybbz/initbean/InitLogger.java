package com.xybbz.initbean;

import com.xybbz.sendlogs.NormalLogSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * 启动项目的时候将日志存到数据库中
 */
@Slf4j
public class InitLogger implements InitializingBean {

    @Value("${spring.application.name}")
    private String aooName;

    public void info (String level,String id, String data) {
        NormalLogSend.sendNormal(level, id, data);
    }

    public void debug(String id, String data) {
        NormalLogSend.sendNormal("debug", id, data);
    }

    public void warn(String id, String data) {
        NormalLogSend.sendNormal("warn", id, data);
    }

    public void error(String id, String data) {
        NormalLogSend.sendNormal("error", id, data);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(aooName + ": 启动成功");
    }
}
