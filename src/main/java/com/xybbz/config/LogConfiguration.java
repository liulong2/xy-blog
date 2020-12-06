package com.xybbz.config;

import com.xybbz.event.ApiLogListener;
import com.xybbz.event.ExceptionLogListener;
import com.xybbz.event.NormalLogListener;
import com.xybbz.modules.log.mapper.LogApiDAO;
import com.xybbz.modules.log.mapper.LogBusinessDAO;
import com.xybbz.modules.log.mapper.LogDAO;
import com.xybbz.modules.log.mapper.LogErrDAO;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class LogConfiguration {


    private final LogDAO logDAO;
    private final LogBusinessDAO logBusinessDAO;
    private final LogErrDAO logErrDAO;
    private final LogApiDAO logApiDAO;

    @Bean
    public ExceptionLogListener exceptionLogListener() {
        return new ExceptionLogListener(logErrDAO,logDAO);
    }

    @Bean
    public NormalLogListener normalLogListener() {
        return new NormalLogListener(logDAO,logBusinessDAO);
    }

    @Bean
    public ApiLogListener apiLogListener() {
        return new ApiLogListener(logApiDAO,logDAO);
    }
}
