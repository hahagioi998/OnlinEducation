package com.hzlei.eduservice.mapper;

import com.hzlei.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzlei.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author hzlei
 * @since 2020-07-05
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    // 课程最终发布时, 返回的信息
    CoursePublishVo getPublishCourseInfo(String courseId);

}
