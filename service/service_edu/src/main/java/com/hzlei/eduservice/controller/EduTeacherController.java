package com.hzlei.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzlei.commonutils.R;
import com.hzlei.eduservice.entity.EduTeacher;
import com.hzlei.eduservice.entity.vo.TeacherQuery;
import com.hzlei.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    // 3, 分页查询
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        // 创建page对象
        Page<EduTeacher> page = new Page<>(current, limit);
        // 调用方法实现分页
        // 调用方法的时候，底层进行了封装，把分页所有的数据封装到page对象里面了
        eduTeacherService.page(page, null);
        // 总记录数
        long total = page.getTotal();
        // 数据list集合
        List<EduTeacher> records = page.getRecords();

        return R.ok().data("total", total).data("rows", records);
    }

    // 4, 条件查询带分页的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacher) {
        // 创建page对象
        Page<EduTeacher> page = new Page<>(current, limit);

        // 构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 判断条件值是否为空,如果不为空拼接条件
        String name = teacher.getName();
        Integer level = teacher.getLevel();
        String begin = teacher.getBegin();
        String end = teacher.getEnd();
        if (!StringUtils.isEmpty(name))
            wrapper.like("name", name);
        if (!StringUtils.isEmpty(level))
            wrapper.eq("level", level);
        if (!StringUtils.isEmpty(begin))
            wrapper.ge("gmt_create", begin); // ge: 大于等于
        if (!StringUtils.isEmpty(end))
            wrapper.le("gmt_create", end); // le: 小于等于

        // 调用方法实现条件查询分页
        eduTeacherService.page(page, wrapper);
        // 总记录数
        long total = page.getTotal();
        // 数据list集合
        List<EduTeacher> records = page.getRecords();

        return R.ok().data("total", total).data("rows", records);
    }

    // 5, 添加讲师
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher teacher) {
        boolean save = eduTeacherService.save(teacher);
        if (save) return R.ok();
        else return R.error();
    }


}

