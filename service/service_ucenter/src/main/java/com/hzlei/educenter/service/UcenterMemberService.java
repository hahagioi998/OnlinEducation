package com.hzlei.educenter.service;

import com.hzlei.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzlei.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author hzlei
 * @since 2020-10-16
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    // 登录
    String loginUser(UcenterMember member);

    // 注册
    void register(RegisterVo register);
}
