package com.hzlei.eduservice.feginclient.hystrix;

import com.hzlei.commonutils.R;
import com.hzlei.eduservice.feginclient.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 熔断操作
 * @Author hzlei
 * @Date 2020/7/27 21:24
 */
@Component
public class VodFileDegradeFeginClient implements VodClient {

    @Override
    public R removeVideoByVideoId(String videoId) {
        return R.error().message("删除视频失败-hystrix");
    }

    @Override
    public R removeVideoByVideoIds(List<String> videoIds) {
        return R.error().message("删除视频失败-hystrix");
    }
}
