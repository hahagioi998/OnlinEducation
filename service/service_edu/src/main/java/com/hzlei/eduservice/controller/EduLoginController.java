package com.hzlei.eduservice.controller;

import com.hzlei.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 登录
 * @Author hzlei
 * @Date 2020/6/27 13:58
 */
// 解决跨域
@CrossOrigin
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    // login
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    // info
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]")
                .data("name", "善逸")
                .data("avator", "***.img");
    }

}
