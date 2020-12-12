package com.hzlei.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzlei.eduservice.entity.EduTeacher;
import com.hzlei.eduservice.mapper.EduTeacherMapper;
import com.hzlei.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author hzlei
 * @since 2020-06-16
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    /**
     * 分页查询讲师
     *
     * @param teacherPage 讲师 page 对象
     * @return
     */
    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> teacherPage) {
        // 返回对象, 存放查询出来的数据
        Map<String, Object> map = new HashMap<>();

        // 查询条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        // 查询
        baseMapper.selectPage(teacherPage, queryWrapper);

        // 查询出来的讲师数据集合
        map.put("records", teacherPage.getRecords());
        // 当前页
        map.put("current", teacherPage.getCurrent());
        // 总页数
        map.put("pages", teacherPage.getPages());
        // 每页数据条数
        map.put("size", teacherPage.getSize());
        // 总数据条数
        map.put("total", teacherPage.getTotal());
        // 是否有下一页
        map.put("hasNext", teacherPage.hasNext());
        // 是否有上一页
        map.put("hasPrevious", teacherPage.hasPrevious());

        return map;
    }
}
