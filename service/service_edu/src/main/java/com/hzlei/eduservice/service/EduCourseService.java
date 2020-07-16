package com.hzlei.eduservice.service;

import com.hzlei.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzlei.eduservice.entity.vo.CourseInfoVo;
import com.hzlei.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author hzlei
 * @since 2020-07-05
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo course);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String courseId);
}
