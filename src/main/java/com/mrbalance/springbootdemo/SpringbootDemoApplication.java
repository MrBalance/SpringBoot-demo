package com.mrbalance.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.mrbalance.dao")
//@ComponentScan(basePackages = "com.mrbalance.*")
@SpringBootApplication
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
//        SpringApplication application = new SpringApplication(SpringbootDemoApplication.class);
//        application.setBannerMode(Banner.Mode.OFF);
//        application.run(args);
    }

}
