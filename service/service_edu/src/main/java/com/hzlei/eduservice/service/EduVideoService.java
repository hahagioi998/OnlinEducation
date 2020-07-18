package com.hzlei.eduservice.service;

import com.hzlei.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author hzlei
 * @since 2020-07-05
 */
public interface EduVideoService extends IService<EduVideo> {

    // 根据课程 id 删除小节
    void removeVideoByCourseId(String courseId);
}
