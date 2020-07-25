package com.hzlei.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: 启动类
 * @Author hzlei
 * @Date 2020/6/17 20:24
 */
// nacos 注册
@EnableDiscoveryClient
@SpringBootApplication
// 主要是为了扫描到公共模块(common)下的 swagger
@ComponentScan(basePackages = {"com.hzlei"})
// 开启 springboot 事务支持
@EnableTransactionManagement
public class EduServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduServiceApplication.class, args);
    }

}
