package com.xybbz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.xybbz.*"})
@EnableTransactionManagement
//@MapperScan("com.xybbz.*.*.mapper")
public class XyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyBlogApplication.class, args);
    }

}
