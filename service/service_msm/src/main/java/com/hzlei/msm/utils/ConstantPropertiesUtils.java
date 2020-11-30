package com.hzlei.msm.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author hzlei
 * @Date 2020/7/1 19:51
 * @Description: 读取配置文件中的内容
 */
// 当项目一启动, spring 接口, spring 加载之后, 执行接口的一个方法
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    // key
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;
    // 密钥
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;
    // 阿里云短信服务
    @Value("${aliyun.msm.signname}")
    private String signName;
    // 阿里云短信服务
    @Value("${aliyun.msm.templatecode}")
    private String templatecode;

    // 定义公共静态常量
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String SIGN_NAME;
    public static String TEMPLATE_CODE;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        SIGN_NAME = signName;
        TEMPLATE_CODE = templatecode;
    }
}
