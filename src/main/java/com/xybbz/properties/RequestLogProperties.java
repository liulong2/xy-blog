package com.xybbz.properties;

import com.xybbz.log.enums.LogLevelEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
//@RefreshScope
@ConfigurationProperties(LogLevelEnum.LOG_PROPS_PREFIX)
public class RequestLogProperties {
    /**
     * 是否开启请求日志
     */
    private Boolean enabled = true;

    /**
     * 日志级别配置，默认：BODY
     */
    private LogLevelEnum level = LogLevelEnum.BODY;
}
