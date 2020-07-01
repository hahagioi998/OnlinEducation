package com.hzlei.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: 读取配置文件中的内容
 * @Author hzlei
 * @Date 2020/7/1 19:51
 */
// 当项目一启动, spring 接口, spring 加载之后, 执行接口的一个方法
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    // 地域节点
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    // key
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    // 密钥
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    // bucketName
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    // 定义公共静态常量
    public static  String END_POINT;
    public static  String ACCESS_KEY_ID;
    public static  String ACCESS_KEY_SECRET;
    public static  String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
