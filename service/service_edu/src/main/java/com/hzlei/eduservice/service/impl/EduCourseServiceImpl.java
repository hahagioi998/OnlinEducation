package com.hzlei.eduservice.service.impl;

import com.hzlei.eduservice.entity.EduCourse;
import com.hzlei.eduservice.entity.EduCourseDescription;
import com.hzlei.eduservice.entity.vo.CourseInfoVo;
import com.hzlei.eduservice.mapper.EduCourseMapper;
import com.hzlei.eduservice.service.EduCourseDescriptionService;
import com.hzlei.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzlei.servicebase.exceptionhandler.HzleiException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 课程表, 存储课程的基本信息 服务实现类
 * </p>
 *
 * @author hzlei
 * @since 2020-07-05
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Resource
    private EduCourseDescriptionService courseDescriptionService;

    // 添加课程基本信息
    @Override
    public void saveCourseInfo(CourseInfoVo course) {
        // 1. 向课程表(edu_course)添加课程基本信息
        // 将 CourseInfoVo 对象转换成 EduCourse 对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(course, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0) throw new HzleiException(20001, "添加课程信息失败");

        // 2. 向课程简介表(edu_course_description)添加课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(eduCourse.getId());
        courseDescription.setDescription(course.getDescription());
        courseDescriptionService.save(courseDescription);
    }
}
