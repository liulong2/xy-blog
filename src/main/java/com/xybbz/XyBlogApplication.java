package com.xybbz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xybbz.*.*.mapper")
public class XyBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(XyBlogApplication.class, args);
	}

}
