package com.hzlei.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzlei.commonutils.R;
import com.hzlei.eduservice.entity.EduCourse;
import com.hzlei.eduservice.entity.EduTeacher;
import com.hzlei.eduservice.service.EduCourseService;
import com.hzlei.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author hzlei
 * @Date 2020/12/12 20:34
 * @Description: 前台页面讲师页面接口
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacherFront")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;

    /**
     * 分页查询讲师
     *
     * @param page 当前页
     * @param limit 每页数据条数
     * @return
     */
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        Map<String, Object> map = teacherService.getTeacherFrontList(teacherPage);

        // 返回分页所有数据
        return R.ok().data(map);
    }

    /**
     * 讲师详情
     *
     * @param teacherId 讲师 id
     * @return
     */
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable String teacherId) {
        // 根据讲师 id 查询讲师基本信息
        EduTeacher teacher = teacherService.getById(teacherId);

        // 根据讲师 id 查询所将课程
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacherId);
        List<EduCourse> courseList = courseService.list(queryWrapper);

        return R.ok().data("teacher", teacher).data("courseList", courseList);
    }

}
