package com.hzlei.eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzlei.commonutils.R;
import com.hzlei.eduservice.entity.EduTeacher;
import com.hzlei.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author hzlei
 * @since 2020-06-16
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    // service注入
    @Autowired
    private EduTeacherService eduTeacherService;

    // 1, 查询讲师表所有数据
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> teachers = eduTeacherService.list(null);
        return R.ok().data("items", teachers);
    }

    // 2, 逻辑删除讲师
    @DeleteMapping("delete/{id}")
    public R removeTeacher(@PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) return R.ok();
        else return R.error();
    }

    // 分页查询
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current, @PathVariable long limit) {
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        // 调用方法实现分页
        // 调用方法的时候，底层进行了封装，把分页所有的数据封装到page对象里面了
        eduTeacherService.page(pageTeacher, null);
        // 总记录数
        long total = pageTeacher.getTotal();
        // 数据list集合
        List<EduTeacher> records = pageTeacher.getRecords();

        return R.ok().data("total", total).data("rows", records);
    }


}

