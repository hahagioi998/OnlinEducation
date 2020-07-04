package com.hzlei.eduservice.service;

import com.hzlei.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzlei.eduservice.entity.tree.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author hzlei
 * @since 2020-07-02
 */
public interface EduSubjectService extends IService<EduSubject> {

    // 添加课程分类
    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllSubject();
}
