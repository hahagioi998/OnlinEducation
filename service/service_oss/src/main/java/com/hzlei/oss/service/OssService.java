package com.hzlei.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author hzlei
 * @Date 2020/7/1 20:08
 * @Description: oss服务 service
 */
public interface OssService {

    // 上传头像到 阿里云OSS
    String uploadFileAvator(MultipartFile file);

}
