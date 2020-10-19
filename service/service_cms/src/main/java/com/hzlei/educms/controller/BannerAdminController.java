package com.hzlei.educms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzlei.commonutils.R;
import com.hzlei.educms.entity.CrmBanner;
import com.hzlei.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 首页banner表 前端控制器, 后台管理员操作
 * </p>
 *
 * @author hzlei
 * @since 2020-08-02
 */
@CrossOrigin
@RestController
@RequestMapping("/educms/bannerAdmin")
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    /**
     * 分页查询
     * @param current 当前页
     * @param limit 每页数量
     * @return
     */
    @GetMapping("pageBanner/{current}/{limit}")
    public R pageBanner(@PathVariable long current, @PathVariable long limit) {
        Page<CrmBanner> page = new Page<>(current, limit);

        bannerService.page(page, null);

        return R.ok().data("items", page.getRecords()).data("total", page.getTotal());
    }

    /**
     * 添加 banner
     * @param banner
     * @return
     */
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner banner) {
        bannerService.save(banner);
        return R.ok();
    }

    /**
     * 修改 banner 信息
     * @param banner
     * @return
     */
    @PutMapping("updateById")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return R.ok();
    }

    /**
     * 删除 banner
     * @param id
     * @return
     */
    @DeleteMapping("removeBanner/{id}")
    public R removeBanner(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }

    /**
     * 根据 id 获取 banner 信息
     * @param id
     * @return
     */
    @GetMapping("getBannerById/{id}")
    public R getBannerById(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item", banner);
    }



}

