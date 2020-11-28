package com.hzlei.msm.controller;

import com.hzlei.commonutils.R;
import com.hzlei.msm.service.MsmService;
import com.hzlei.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author hzlei
 * @Date 2020/10/14 21:51
 * @Description: 短信服务 controller
 */
@CrossOrigin
@RestController
@RequestMapping("/edumsm/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发送短信的方法
     *
     * @param phone 手机号
     * @return
     */
    @GetMapping("sendSMS/{phone}")
    public R sendSMS(@PathVariable String phone) {
        // 从 redis 中获取验证码，如果获取到，直接返回
        String code = redisTemplate.opsForValue().get(phone);

        // 判断 验证码 不为空，直接返回
        if (!StringUtils.isEmpty(code)) return R.ok();

        // 生成随机值，传递给阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);

        // 调用 service 发送短信的方法
        boolean isSend = msmService.sendSMS(param, phone);

        // 判断
        if (isSend) {
            /*
             * 将验证码存入 redis，并设置 redis 验证码有效时间
             * phone: 手机号
             * code: 验证码
             * 5: 有效时长
             * TimeUnit.MINUTES: 有效时长单位
             */
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return R.ok();
        } else return R.error().message("短信发送失败");
    }

}
