package com.xybbz.config;

import com.xybbz.event.ApiLogListener;
import com.xybbz.event.ExceptionLogListener;
import com.xybbz.event.NormalLogListener;
import com.xybbz.modules.log.mapper.LogApiDAO;
import com.xybbz.properties.RequestLogProperties;
import com.xybbz.initbean.InitLogger;
import com.xybbz.modules.log.mapper.LogBusinessDAO;
import com.xybbz.modules.log.mapper.LogDAO;
import com.xybbz.modules.log.mapper.LogErrDAO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(RequestLogProperties.class)
public class SpringLogConfiguration {


    @Bean
    @ConditionalOnMissingBean(name = "exceptionLogListener")
    public ExceptionLogListener exceptionLogListener(LogErrDAO logErrDAO, LogDAO logDAO) {
        return new ExceptionLogListener(logErrDAO,logDAO);
    }

    @Bean
    @ConditionalOnMissingBean(name = "normalLogListener")
    public NormalLogListener normalLogListener(LogDAO logDAO, LogBusinessDAO logBusinessDAO) {
        return new NormalLogListener(logDAO,logBusinessDAO);
    }

    @Bean
    @ConditionalOnMissingBean(name = "apiLogListener")
    public ApiLogListener apiLogListener(LogApiDAO logApiDAO, LogDAO logDAO) {
        return new ApiLogListener(logApiDAO,logDAO);
    }

    @Bean
    public InitLogger initLogger() {
        return new InitLogger();
    }
}
