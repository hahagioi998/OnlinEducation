package com.hzlei.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzlei.commonutils.R;
import com.hzlei.eduservice.entity.EduCourse;
import com.hzlei.eduservice.entity.EduTeacher;
import com.hzlei.eduservice.service.EduCourseService;
import com.hzlei.eduservice.service.EduTeacherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 前端首页面数据
 * @Author hzlei
 * @Date 2020/8/6 21:14
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {

    @Resource
    private EduCourseService courseService;
    @Resource
    private EduTeacherService teacherService;

    // 8条热门课程数据, 4条名师数据
    @GetMapping("index")
    public R index() {
        // 8条热门课程数据
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("id");
        courseQueryWrapper.last("limit 8");
        courseQueryWrapper.eq("status", "Normal");

        List<EduCourse> courses = courseService.list(courseQueryWrapper);

        // 4条名师数据
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByAsc("id");
        teacherQueryWrapper.last("limit 4");

        List<EduTeacher> teachers = teacherService.list(teacherQueryWrapper);

        return R.ok().data("courses", courses).data("teachers", teachers);
    }

}
