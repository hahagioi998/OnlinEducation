package com.hzlei.eduservice.service.impl;

import com.hzlei.eduservice.entity.EduCourse;
import com.hzlei.eduservice.entity.EduCourseDescription;
import com.hzlei.eduservice.entity.vo.CourseInfoVo;
import com.hzlei.eduservice.entity.vo.CoursePublishVo;
import com.hzlei.eduservice.mapper.EduCourseMapper;
import com.hzlei.eduservice.service.EduCourseDescriptionService;
import com.hzlei.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzlei.servicebase.exceptionhandler.HzleiException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public String saveCourseInfo(CourseInfoVo course) {
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
        return eduCourse.getId();
    }

    /**
     *  查询课程基本信息
     * @param courseId 课程id
     * @return
     */
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        // 定义返回的数据
        CourseInfoVo courseInfoVo = new CourseInfoVo();

        // 1. 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        // 2. 查询课程描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);

        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        BeanUtils.copyProperties(courseDescription, courseInfoVo);

        return courseInfoVo;
    }

    /**
     * 修改课程信息
     * @param courseInfoVo 课程描述对象
     * @return
     */
    @Transactional // 事务
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        // 1. 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int updateEducourse = baseMapper.updateById(eduCourse);

        // 2. 修改课程描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, eduCourseDescription);
        boolean updateEduCourseDesc = courseDescriptionService.updateById(eduCourseDescription);

        if (updateEducourse == 0 || updateEduCourseDesc == false)
            throw new HzleiException(20001, "修改课程信息失败");
    }

    /**
     * 根据课程id（courseId）查询课程最终确认信息
     * @param courseId 课程 id
     * @return
     */
    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        // 调用 mapper
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);
        return publishCourseInfo;
    }
}
