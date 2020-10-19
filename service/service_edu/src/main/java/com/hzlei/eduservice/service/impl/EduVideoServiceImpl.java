package com.hzlei.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzlei.eduservice.entity.EduVideo;
import com.hzlei.eduservice.feginclient.VodClient;
import com.hzlei.eduservice.mapper.EduVideoMapper;
import com.hzlei.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author hzlei
 * @since 2020-07-05
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    /**
     * 根据课程 id 删除小节
     * @param courseId: 课程 id
     */
    @Override
    public void removeVideoByCourseId(String courseId) {
        // 根据课程 id 查询课程所有小节里面的视频
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapperVideo);

        // 取出查询结果中的视频 id
        List<String> videoIds = new ArrayList<>();
        for (EduVideo eduVideo : eduVideos) {
            if (!StringUtils.isEmpty(eduVideo.getVideoSourceId()))
                videoIds.add(eduVideo.getVideoSourceId());
        }

        if (!videoIds.isEmpty()) {
            // 调用方法删除视频
            vodClient.removeVideoByVideoIds(videoIds);
        }

        // 删除小节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
