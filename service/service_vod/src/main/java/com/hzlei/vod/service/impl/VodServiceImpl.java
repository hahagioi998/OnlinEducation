package com.hzlei.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.hzlei.servicebase.exceptionhandler.HzleiException;
import com.hzlei.vod.service.VodService;
import com.hzlei.vod.utils.ConstantVodUtils;
import com.hzlei.vod.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @Author hzlei
 * @Date 2020/7/21 22:02
 * @Description: 上传视频到阿里云
 */
@Service
public class VodServiceImpl implements VodService {

    /**
     * 上传视频到阿里云
     *
     * @param file
     * @return
     */
    @Override
    public String uploadAliyunVideo(MultipartFile file) {
        // 阿里云返回的视频 id
        String videoId = "";
        try {
            /*
             * accessKeyId
             * accessKeySecret
             * title: 上传之后, 阿里云显示的名称
             * fileName: 上传文件原始名称
             * inputStream: 上传文件输入流
             */
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            // 请求视频点播服务的请求ID
            System.out.print("RequestId = " + response.getRequestId() + "\n");
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                // 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除 阿里云 云端视频
     *
     * @param videoId 视频 id
     * @return
     */
    @Override
    public void removeVideoByVideoId(String videoId) {
        try {
            // 初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            // 创建删除视频 request 对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            // 向 request 中设置视频 id
            request.setVideoIds(videoId);
            // 调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HzleiException(20001, "视频删除失败");
        }
    }

    /**
     * 删除 多个 阿里云 云端视频
     *
     * @param videoIds 视频 ids
     * @return
     */
    @Override
    public void removeVideoByVideoIds(List<String> videoIds) {
        try {
            // 初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            // 创建删除视频 request 对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            // 向 request 中设置视频 id
            // request.setVideoIds("videoId1, videoId2, videoId3");
            String videoIdsStr = StringUtils.join(videoIds.toArray(), ",");
            request.setVideoIds(videoIdsStr);
            // 调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HzleiException(20001, "视频删除失败");
        }
    }
}
