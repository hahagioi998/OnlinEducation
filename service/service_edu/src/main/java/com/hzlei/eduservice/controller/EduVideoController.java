package com.hzlei.eduservice.controller;


import com.hzlei.commonutils.R;
import com.hzlei.eduservice.entity.EduVideo;
import com.hzlei.eduservice.service.EduVideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程小节表, 存储章节小节信息 前端控制器
 * </p>
 *
 * @author hzlei
 * @since 2020-07-05
 */
// 解决跨域
@CrossOrigin
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Resource
    private EduVideoService videoService;

    /**
     * 添加小节
     * @param video
     * @return
     */
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo video) {
        videoService.save(video);
        return R.ok();
    }

    /**
     * 删除小节
     * @param id
     * @return
     * TODO 后面需要完善, 删除小节的时候, 小节里面的视频也要删除
     */
    @DeleteMapping("deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id) {
        videoService.removeById(id);
        return R.ok();
    }

    /**
     * 修改小节
     * @param video
     * @return
     */
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo video) {
        videoService.updateById(video);
        return R.ok();
    }

    /**
     * 根据 id 查询小节
     * @param id
     * @return
     */
    @GetMapping("getVideoInfo/{id}")
    public R getVideoInfo(@PathVariable String id) {
        EduVideo video = videoService.getById(id);
        return R.ok().data("video", video);
    }

}

