package com.hzlei.oss.controller;

import com.hzlei.commonutils.R;
import com.hzlei.oss.service.OssService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Description: controller
 * @Author hzlei
 * @Date 2020/7/1 20:08
 */
@CrossOrigin
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Resource
    private OssService ossService;


    // 上传头像
    @PostMapping("")
    public R uploadOssFile(MultipartFile file) {
        // 获取上传对象 MultipartFile
        String avatorUrl = ossService.uploadFileAvator(file);

        return R.ok().data("url", avatorUrl);
    }

}
