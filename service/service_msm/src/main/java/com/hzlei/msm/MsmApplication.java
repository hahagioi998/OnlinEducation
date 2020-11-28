package com.hzlei.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author hzlei
 * @Date 2020/10/14 21:49
 * @Description: 短信服务启动类
 */
@ComponentScan("com.hzlei")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }

}
