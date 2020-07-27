package com.hzlei.eduservice.feginclient;

import com.hzlei.commonutils.R;
import com.hzlei.eduservice.feginclient.hystrix.VodFileDegradeFeginClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: service-vod
 * @Author hzlei
 * @Date 2020/7/26 14:51
 */
@Component // 防止在其他位置注入 VodClient时 idea 报错
// 指从哪个服务中调用功能, service-vod: vod 端(服务端) 在 nacos 注册的服务名字
// fallback = VodFileDegradeFeginClient.class, 指出现熔断,隔离降级之后会出现的操作类
@FeignClient(value = "service-vod", fallback = VodFileDegradeFeginClient.class)
public interface VodClient {

    /*
     * 定义方法, 调用 service-vod 中的方法
     * 根据视频 id 删除阿里云中的视频
     * @PathVariable("videoId") 注解一定要指定参数名称, 否则出错
     */
    @DeleteMapping("/eduvod/video/removeVideoByVideoId/{videoId}")
    public R removeVideoByVideoId(@PathVariable("videoId") String videoId);

    // 删除 多个 阿里云 云端视频
    @DeleteMapping("/eduvod/video/removeVideoByVideoIds")
    public R removeVideoByVideoIds(@RequestParam("videoIds") List<String> videoIds);

}
