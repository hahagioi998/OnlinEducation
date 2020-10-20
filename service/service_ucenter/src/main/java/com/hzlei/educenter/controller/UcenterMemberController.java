package com.hzlei.educenter.controller;

import com.hzlei.commonutils.JwtUtils;
import com.hzlei.commonutils.R;
import com.hzlei.educenter.entity.UcenterMember;
import com.hzlei.educenter.entity.vo.RegisterVo;
import com.hzlei.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author hzlei
 * @since 2020-10-16
 */
@CrossOrigin
@RestController
@RequestMapping("/educenter/ucenter")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    /**
     * 登录
     * @param member 手机号密码对象
     * @return token
     */
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member) {
        // 调用 service 里面的方法, 实现登录
        // 返回 token 值, 调用 jwt 生成
        String token = memberService.loginUser(member);
        return R.ok().data("token", token);
    }

    // 注册
    @PostMapping("register")
    public R register(@RequestBody RegisterVo register) {
        memberService.register(register);
        return R.ok();
    }

    // 根据 token 获取用户信息
    @GetMapping("getUserInfo")
    public R getUserInfoByToken(HttpServletRequest request) {
        // 调用 jwt 工具类中的方法
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        // 根据用户 id 查询用户信息
        UcenterMember userInfo = memberService.getById(userId);
        return R.ok().data("userInfo", userInfo);
    }

}

