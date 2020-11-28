package com.hzlei.vod.controller;

import com.hzlei.commonutils.R;
import com.hzlei.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author hzlei
 * @Date 2020/7/21 22:01
 * @Description: 云点播
 */
@CrossOrigin
@RestController
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * 上传视频到阿里云
     *
     * @param file
     * @return
     */
    @PostMapping("uploadAliyunVideo")
    public R uploadAliyunVideo(MultipartFile file) {
        String videoId = vodService.uploadAliyunVideo(file);
        return R.ok().data("videoId", videoId);
    }

    /**
     * 删除 阿里云 云端视频
     *
     * @param videoId 视频 id
     * @return
     */
    @DeleteMapping("removeVideoByVideoId/{videoId}")
    public R removeVideoByVideoId(@PathVariable String videoId) {
        vodService.removeVideoByVideoId(videoId);
        return R.ok();
    }

    /**
     * 删除 多个 阿里云 云端视频
     *
     * @param videoIds 视频 ids
     * @return
     */
    @DeleteMapping("removeVideoByVideoIds")
    public R removeVideoByVideoIds(@RequestParam("videoIds") List<String> videoIds) {
        vodService.removeVideoByVideoIds(videoIds);
        return R.ok();
    }

}
