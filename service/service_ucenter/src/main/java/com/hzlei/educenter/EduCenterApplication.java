package com.hzlei.educenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: TODO:
 * @Author hzlei
 * @Date 2020/10/16 0016 22:46
 */
@SpringBootApplication
// 主要是为了扫描到公共模块(common)下的 swagger
@ComponentScan(basePackages = {"com.hzlei"})
// nacos 注册
//@EnableDiscoveryClient
// 服务调用(调用端/客户端)
//@EnableFeignClients
public class EduCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduCenterApplication.class, args);
    }

}
