package com.hzlei.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.hzlei.oss.service.OssService;
import com.hzlei.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author hzlei
 * @Date 2020/7/1 20:09
 * @Description: 上传文件至 OSS
 */
@Service
public class OssServiceImpl implements OssService {

    // 上传头像到 阿里云OSS
    @Override
    public String uploadFileAvator(MultipartFile file) {
        // 返回文件路径, 需要手动拼接
        String url = "";

        // 通过工具类获取参数值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 获取文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String fileName = uuid + "-" + file.getOriginalFilename();
        // 当前日期, 2020-07
        String dataPath = new DateTime().toString("yyyy-MM");
        // 2020-07/qwe-aaa.png
        fileName = dataPath + "/" + fileName;

        // 创建 OSSClient 实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 上传文件流
            InputStream inputStream = file.getInputStream();

            /*
             * 第一个参数: bucketName
             * 第二个参数: 上传到 oss文件路径和文件名称
             * 第三个参数: 上传文件输入流
             */
            ossClient.putObject(bucketName, fileName, inputStream);

            url = "https://" + bucketName + "." + endpoint + "/" + fileName;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭 OSSClient
            ossClient.shutdown();
        }
        return url;
    }
}
