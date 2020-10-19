package com.hzlei.eduservice.controller;


import com.hzlei.commonutils.R;
import com.hzlei.eduservice.entity.tree.OneSubject;
import com.hzlei.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author hzlei
 * @since 2020-07-02
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 添加课程分类
     * 获取上传过来的文件, 把文件内容读取出来
     *
     * @param file 传过来的文件
     * @return
     */
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        eduSubjectService.saveSubject(file, eduSubjectService);
        return R.ok();
    }

    // 课程分类列表
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        List<OneSubject> data = eduSubjectService.getAllSubject();
        return R.ok().data("data", data);
    }



}

