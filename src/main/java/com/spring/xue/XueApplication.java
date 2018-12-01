package com.spring.xue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan("com.spring.xue.mapper")
public class XueApplication {

    public static void main(String[] args) {
        SpringApplication.run(XueApplication.class, args);
    }
}
