package com.xybbz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication(scanBasePackages = {"com.xybbz.*"})
@EnableTransactionManagement
//@MapperScan("com.xybbz.*.*.mapper")
public class XyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyBlogApplication.class, args);
    }

}
