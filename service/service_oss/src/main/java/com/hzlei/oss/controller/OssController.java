package com.hzlei.oss.controller;

import com.hzlei.commonutils.R;
import com.hzlei.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author hzlei
 * @Date 2020/7/1 20:08
 * @Description: oss服务 controller
 */
@CrossOrigin
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    // 上传头像
    @PostMapping("")
    public R uploadOssFile(MultipartFile file) {
        // 获取上传对象 MultipartFile
        String avatarUrl = ossService.uploadFileAvator(file);
        return R.ok().data("url", avatarUrl);
    }

}
