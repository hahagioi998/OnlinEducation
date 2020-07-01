package com.hzlei.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: service
 * @Author hzlei
 * @Date 2020/7/1 20:08
 */
public interface OssService {

    // 上传头像到 阿里云OSS
    String uploadFileAvator(MultipartFile file);

}
