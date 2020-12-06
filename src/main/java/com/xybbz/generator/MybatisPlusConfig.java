package com.xybbz.generator;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.xybbz.generator.PageConstant.PACKAGE_NAME;

@Configuration
//@MapperScan(PACKAGE_NAME + ".*.*.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        return new PaginationInnerInterceptor();
    }
}
