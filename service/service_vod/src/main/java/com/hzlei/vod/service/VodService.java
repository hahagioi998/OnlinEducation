package com.hzlei.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author hzlei
 * @Date 2020/7/21 22:02
 * @Description: service
 */
public interface VodService {
    // 上传视频到阿里云
    String uploadAliyunVideo(MultipartFile file);

    void removeVideoByVideoId(String videoId);

    void removeVideoByVideoIds(List<String> videoIds);
}
