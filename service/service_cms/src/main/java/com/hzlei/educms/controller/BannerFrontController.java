package com.hzlei.educms.controller;


import com.hzlei.commonutils.R;
import com.hzlei.educms.entity.CrmBanner;
import com.hzlei.educms.service.CrmBannerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器, 前台页面操作
 * </p>
 *
 * @author hzlei
 * @since 2020-08-02
 */
@CrossOrigin
@RestController
@RequestMapping("/educms/bannerFront")
public class BannerFrontController {

    @Resource
    private CrmBannerService bannerService;

    // 查询所有 banner
    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> banners = bannerService.getAllBanner();
        return R.ok().data("banners", banners);
    }

}

