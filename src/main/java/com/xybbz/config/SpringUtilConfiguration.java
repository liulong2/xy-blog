package com.xybbz.config;

import com.xybbz.util.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringUtilConfiguration {
    /**
     * Spring上下文缓存
     */
    @Bean
    public SpringUtil springUtil() {
        return new SpringUtil();
    }
}
