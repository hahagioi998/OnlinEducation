package com.hzlei.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.hzlei.msm.service.MsmService;
import com.hzlei.msm.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @Author hzlei
 * @Date 2020/10/14 22:02
 * @Description: 发送手机短信验证码
 */
@Service
public class MsmServiceImpl implements MsmService {

    /**
     * 发送手机短信验证码
     *
     * @param param 随机验证码
     * @param phone 手机号
     * @return
     */
    @Override
    public boolean sendSMS(Map<String, Object> param, String phone) {
        // 通过工具类获取参数值
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String signName = ConstantPropertiesUtils.SIGN_NAME;
        String templateCode = ConstantPropertiesUtils.TEMPLATE_CODE;

        // 判断手机号是否为空
        if (StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile = DefaultProfile
                .getProfile("default", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        // 设置相关参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setSysAction("SendSms");

        // 设置发送相关的参数
        request.putQueryParameter("PhoneNumbers", phone); // 发送的手机号
        request.putQueryParameter("SignName", signName); // 申请的阿里云短信服务 签名名称
        request.putQueryParameter("TemplateCode", templateCode); // 申请的阿里云短信服务 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); // 验证码(json格式)

        try {
            // 最终发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }

    }
}
