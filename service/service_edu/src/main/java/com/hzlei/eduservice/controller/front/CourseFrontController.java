package com.hzlei.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzlei.commonutils.R;
import com.hzlei.eduservice.entity.EduCourse;
import com.hzlei.eduservice.entity.EduTeacher;
import com.hzlei.eduservice.entity.frontvo.CourseFrontVo;
import com.hzlei.eduservice.service.EduCourseService;
import com.hzlei.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author hzlei
 * @Date 2020/12/12 20:34
 * @Description: 前台页面课程页面接口
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/courseFront")
public class CourseFrontController {

    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;

    /**
     * 课程 条件查询带分页
     *
     * @param page 当前页
     * @param limit 每页条数
     * @param courseFrontVo 待查询条件
     * @return
     */
    @PostMapping("getCourseFrontList/{page}/{limit}")
    public R getCourseFrontList(@PathVariable long page,
                                @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontList(pageCourse, courseFrontVo);
        return R.ok().data(map);
    }

}
