package com.hzlei.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzlei.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author hzlei
 * @since 2020-06-16
 */
public interface EduTeacherService extends IService<EduTeacher> {
    // 分页查询讲师
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> teacherPage);
}
