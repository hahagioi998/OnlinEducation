package com.hzlei.eduservice.controller;


import com.hzlei.commonutils.R;
import com.hzlei.eduservice.entity.EduVideo;
import com.hzlei.eduservice.feginclient.VodClient;
import com.hzlei.eduservice.service.EduVideoService;
import com.hzlei.servicebase.exceptionhandler.HzleiException;
import org.springframework.util.StringUtils;
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
    @Resource
    private VodClient vodClient;

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
     * @param id 小节 id
     * @return
     * TODO 后面需要完善, 删除小节的时候, 小节里面的视频也要删除
     */
    @DeleteMapping("deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id) {
        // 根据小节 id 得到 视频 id
        String videoId = videoService.getById(id).getVideoSourceId();
        // 判断小节里面是否有视频 id
        if (!StringUtils.isEmpty(videoId)) {
            // 根据视频 id, 实现远程调用, 删除视频
            R r = vodClient.removeVideoByVideoId(videoId);
            if (r.getCode() == 20001) {
                throw new HzleiException(20001, "删除视频出错了 ---> by hystrix tip");
            }
        }
        // 删除小节
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

