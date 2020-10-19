package com.hzlei.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzlei.commonutils.JwtUtils;
import com.hzlei.commonutils.MD5;
import com.hzlei.educenter.entity.UcenterMember;
import com.hzlei.educenter.entity.vo.RegisterVo;
import com.hzlei.educenter.mapper.UcenterMemberMapper;
import com.hzlei.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzlei.servicebase.exceptionhandler.HzleiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author hzlei
 * @since 2020-10-16
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 登录
     * @param member 手机号密码对象
     * @return token
     */
    @Override
    public String loginUser(UcenterMember member) {
        // 获取传递过来的参数
        String mobile = member.getMobile();
        String password = member.getPassword();

        // 手机号和密码 非空判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password))
            throw new HzleiException(20001, "登录失败");

        // 手机号是否正确
        QueryWrapper<UcenterMember> mobileWrapper = new QueryWrapper<>();
        mobileWrapper.eq("mobile", mobile);
        UcenterMember mobileMember = baseMapper.selectOne(mobileWrapper);
        // 判断查询出来的手机号是否为空
        if (mobileMember == null)
            throw new HzleiException(20001, "手机号不存在");

        // 密码是否正确
        if (!MD5.encrypt(password).equals(mobileMember.getPassword()))
            throw new HzleiException(20001, "密码错误");

        // 判断用户账号是否被禁用
        if (mobileMember.getIsDisabled())
            throw new HzleiException(20001, "账号被禁用");

        // 走到这里就能登录成功
        // 根据 id, 昵称, 生成 token
        String token = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());

        // 返回 token
        return token;
    }

    // 注册
    @Override
    public void register(RegisterVo register) {
        // 获取传递过来的参数
        String code = register.getCode();
        String nickname = register.getNickname();
        String mobile = register.getMobile();
        String password = register.getPassword();

        // 非空判断
        if (StringUtils.isEmpty(code) ||
            StringUtils.isEmpty(nickname) ||
            StringUtils.isEmpty(mobile) ||
            StringUtils.isEmpty(password))
            throw new HzleiException(20001, "注册失败");

        // 手机验证码是否正确
        // 获取 redis 里面的验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode))
            throw new HzleiException(20001, "验证码错误");

        // 判断手机号是否已经注册
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(queryWrapper);

        if (count > 0) throw new HzleiException(20001, "该手机号已注册");

        // 代码走到这里, 表示可以注册, 把数据添加到数据库
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        baseMapper.insert(member);
    }
}
