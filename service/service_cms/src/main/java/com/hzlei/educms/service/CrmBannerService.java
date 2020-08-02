package com.hzlei.educms.service;

import com.hzlei.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author hzlei
 * @since 2020-08-02
 */
public interface CrmBannerService extends IService<CrmBanner> {
    // 查询所有 banner
    List<CrmBanner> getAllBanner();
}
