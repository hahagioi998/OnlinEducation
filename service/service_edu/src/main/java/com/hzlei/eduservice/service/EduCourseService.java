package com.hzlei.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzlei.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzlei.eduservice.entity.frontvo.CourseFrontVo;
import com.hzlei.eduservice.entity.vo.CourseInfoVo;
import com.hzlei.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

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

    void removeCourse(String courseId);

    // 课程 条件查询带分页
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);
}
