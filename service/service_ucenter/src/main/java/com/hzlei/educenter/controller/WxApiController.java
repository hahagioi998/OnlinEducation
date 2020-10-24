package com.hzlei.educenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.hzlei.commonutils.JwtUtils;
import com.hzlei.educenter.entity.UcenterMember;
import com.hzlei.educenter.service.UcenterMemberService;
import com.hzlei.educenter.utils.ConstantWxUtils;
import com.hzlei.educenter.utils.HttpClientUtils;
import com.hzlei.servicebase.exceptionhandler.HzleiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @Description: 微信 controller
 * @Author hzlei
 * @Date 2020/10/24 0024 16:57
 */
@CrossOrigin
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UcenterMemberService memberService;

    // 生成微信扫描二维码
    @GetMapping("login")
    public String getWxCode() {
//        // 固定地址, 后面拼接参数
//        String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + ConstantWxUtils.WX_OPEN_ID + "&response_type=code";
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        // 对 redirect_url 进行 URLEncoder 编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_ID,
                redirectUrl,
                "hzlei"
        );

        // 重定向到请求地址
        return "redirect:" + url;
    }

    // 获取扫描人的信息, 添加数据
    @GetMapping("callback")
    public String callback(String code, String state) {
        try {
            // 拿着 code 请求 微信固定地址, 得到两个值: accsess_token 和 openid
            String baseAccsessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";

            /*
             * 拼接三个参数
             * appid
             * secret 密钥
             * code
             */
            String accessTokenUrl = String.format(
                    baseAccsessTokenUrl,
                    ConstantWxUtils.WX_OPEN_ID,
                    ConstantWxUtils.WX_OPEN_SECRET,
                    code
            );

            /*
             * 请求 accessTokenUrl, 得到两个参数
             * accsecc_token
             * openid
             */
            // 使用 httpclient 发送请求
            String accseccTokenInfo = HttpClientUtils.get(accessTokenUrl);

            /*
             * 从 accseccTokenInfo 取出来 2个值
             * access_token
             * openid
             */
            System.out.println(accseccTokenInfo);
            HashMap hashMap = JSONObject.parseObject(accseccTokenInfo, HashMap.class);
            String access_token = (String) hashMap.get("access_token");
            String openid = (String) hashMap.get("openid");

            // 将信息存入到数据库
            // 判断数据库有没有相同的用户, 根据 openid 判断
            UcenterMember member = memberService.getOpenIdMemBer(openid);
            if (member == null) {
                // 拿着 access_token, openid 请求 微信固定地址, 获取用户信息
                String baseUserInfo = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(
                        baseUserInfo,
                        access_token,
                        openid
                );

                // 发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);
                HashMap userInfoMap = JSONObject.parseObject(userInfo, HashMap.class);
                String nickname = (String) userInfoMap.get("nickname");
                String headimgurl = (String) userInfoMap.get("headimgurl");
                int sex = (Integer) userInfoMap.get("sex");

                // 如果为 null, 就向数据库添加
                member = new UcenterMember();
                member.setAvatar(headimgurl);
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setSex(sex);
                memberService.save(member);
            }

            // 使用 jwt 根据 member 对象生成 token 字符串
            String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());

            return "redirect:http://localhost:3000?token=" + token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HzleiException(20001, "登录失败");
        }
    }

}
