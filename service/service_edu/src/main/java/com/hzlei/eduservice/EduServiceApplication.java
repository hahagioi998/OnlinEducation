package com.hzlei.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: 启动类
 * @Author hzlei
 * @Date 2020/6/17 20:24
 */
@SpringBootApplication
// 主要是为了扫描到公共模块(common)下的 swagger
@ComponentScan(basePackages = {"com.hzlei"})
public class EduServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduServiceApplication.class, args);
    }

}
