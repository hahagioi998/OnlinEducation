package com.hzlei.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: TODO:
 * @Author hzlei
 * @Date 2020/10/16 0016 22:46
 */
@SpringBootApplication
// 主要是为了扫描到公共模块(common)下的 swagger
@ComponentScan(basePackages = {"com.hzlei"})
@MapperScan("com.hzlei.educenter.mapper")
public class EduCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduCenterApplication.class, args);
    }

}
