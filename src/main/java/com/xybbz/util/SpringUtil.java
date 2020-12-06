package com.xybbz.util;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.Nullable;


@Slf4j
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    /**
     * 发送事件
     * @param event 事件
     */
    public static void sendEvent(ApplicationEvent event) {
        if (ObjectUtil.isNull(context)) {
            return;
        }
        try {
            context.publishEvent(event);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void setApplicationContext(@Nullable ApplicationContext context) throws BeansException {
        SpringUtil.context = context;
    }
}
