package com.hzlei.msm.service;

import java.util.Map;

/**
 * @Author hzlei
 * @Date 2020/10/14 22:01
 * @Description: 短信服务 service
 */
public interface MsmService {
    // 发送手机短信验证码
    boolean sendSMS(Map<String, Object> param, String phone);
}
