package com.hzlei.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzlei.commonutils.R;
import com.hzlei.eduservice.entity.EduCourse;
import com.hzlei.eduservice.entity.EduTeacher;
import com.hzlei.eduservice.entity.vo.CourseInfoVo;
import com.hzlei.eduservice.entity.vo.CoursePublishVo;
import com.hzlei.eduservice.entity.vo.CourseQuery;
import com.hzlei.eduservice.entity.vo.TeacherQuery;
import com.hzlei.eduservice.service.EduCourseService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程表, 存储课程的基本信息 前端控制器
 * </p>
 *
 * @author hzlei
 * @since 2020-07-05
 */
// 解决跨域
@CrossOrigin
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Resource
    private EduCourseService courseService;

    // 课程列表
    @GetMapping("getCourseList")
    public R getCourseList() {
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list", list);
    }

    /**
     * 课程列表, 条件查询带分页
     * @param current 当前页
     * @param limit 每页记录数
     * @param course 条件查询对象
     * @return
     */
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current,
                                 @PathVariable long limit,
                                 @RequestBody(required = false) CourseQuery course) {
        // 创建page对象
        Page<EduCourse> page = new Page<>(current, limit);

        // 构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        // 判断条件值是否为空,如果不为空拼接条件
        String title = course.getTitle();
        String status = course.getStatus();
        String begin = course.getBegin();
        String end = course.getEnd();
        if (!StringUtils.isEmpty(title))
            wrapper.like("title", title);
        if (!StringUtils.isEmpty(status))
            wrapper.eq("status", status);
        if (!StringUtils.isEmpty(begin))
            wrapper.ge("gmt_create", begin); // ge: 大于等于
        if (!StringUtils.isEmpty(end))
            wrapper.le("gmt_create", end); // le: 小于等于

        // 排序
        wrapper.orderByDesc("gmt_create");

        // 调用方法实现条件查询分页
        courseService.page(page, wrapper);
        // 总记录数
        long total = page.getTotal();
        // 数据list集合
        List<EduCourse> records = page.getRecords();

        return R.ok().data("total", total).data("rows", records);
    }

    // 添加课程基本信息
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo course) {
        // 返回添加后的课程 id
        String id = courseService.saveCourseInfo(course);
        return R.ok().data("courseId", id);
    }

    /**
     *  查询课程基本信息
     * @param courseId 课程id
     * @return
     */
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    /**
     * 修改课程信息
     * @param courseInfoVo 课程描述对象
     * @return
     */
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    /**
     * 根据课程id（courseId）查询课程最终确认信息
     * @param courseId 课程 id
     * @return
     */
    @GetMapping("getPublishCourseInfo/{courseId}")
    public R getPublishCourseInfo(@PathVariable String courseId) {
        CoursePublishVo coursePublish = courseService.getPublishCourseInfo(courseId);
        return R.ok().data("publishCourse", coursePublish);
    }

    // 1
    @PostMapping("publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId) {
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus("Normal");
        courseService.updateById(course);
        return R.ok();
    }

}

