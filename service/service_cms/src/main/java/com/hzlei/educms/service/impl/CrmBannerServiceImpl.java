package com.hzlei.educms.service.impl;

import com.hzlei.educms.entity.CrmBanner;
import com.hzlei.educms.mapper.CrmBannerMapper;
import com.hzlei.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author hzlei
 * @since 2020-08-02
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    // 查询所有 banner
    @Override
    public List<CrmBanner> getAllBanner() {
        List<CrmBanner> banners = baseMapper.selectList(null);
        return banners;
    }
}
