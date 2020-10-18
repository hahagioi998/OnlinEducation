package com.hzlei.educenter.controller;


import com.hzlei.commonutils.R;
import com.hzlei.educenter.entity.UcenterMember;
import com.hzlei.educenter.service.UcenterMemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @Resource
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

}

