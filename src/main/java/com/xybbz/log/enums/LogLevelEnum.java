package com.xybbz.log.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LogLevelEnum {
    /**
     * 不开启日志
     */
    NONE(0),
    /**
     * 记录请求
     */
    REQUEST(1),
    /**
     * 记录请求和响应,请求头
     */
    HEADERS(2),
    /**
     * 全部记录
     */
    BODY(3);

    /**
     * 请求日志配置前缀
     */
    public static final String LOG_PROPS_PREFIX = "xy.log";

    private final int level;

}
