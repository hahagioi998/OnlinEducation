package com.hzlei.eduservice.controller;


import com.hzlei.commonutils.R;
import com.hzlei.eduservice.entity.vo.CourseInfoVo;
import com.hzlei.eduservice.service.EduCourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    // 添加课程基本信息
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo course) {
        courseService.saveCourseInfo(course);
        return R.ok();
    }

}

