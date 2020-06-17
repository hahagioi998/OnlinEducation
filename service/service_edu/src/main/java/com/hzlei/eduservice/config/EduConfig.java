package com.hzlei.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 配置类
 * @Author hzlei
 * @Date 2020/6/17 20:26
 */
@Configuration
@MapperScan("com.hzlei.eduservice.mapper")
public class EduConfig {
}
