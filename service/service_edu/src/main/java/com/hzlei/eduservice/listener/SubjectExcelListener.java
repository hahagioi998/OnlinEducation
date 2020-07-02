package com.hzlei.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzlei.eduservice.entity.EduSubject;
import com.hzlei.eduservice.entity.excel.SubjectData;
import com.hzlei.eduservice.service.EduSubjectService;
import com.hzlei.servicebase.exceptionhandler.HzleiException;

/**
 * @Description: 监听器
 * @Author hzlei
 * @Date 2020/7/2 22:47
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    // 因为 SubjectExcelListener 不能交给 spring 进行管理, 要自己 new, 不能注入其他对象
    // 不能实现数据库操作
    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    // 读取 excel 内容, 一行一行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null)
            throw new HzleiException(20001, "文件数据为空");

        // 因为是一行一行读取的, 每次读取都有两个值, 第一个值是一级分类, 第二个值是二级分类
        // 判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectData.getOneSubjectName(), eduSubjectService);
        // 没有相同的一级分类, 进行添加
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(existOneSubject);
        }

        // 获取二级分类的 parent_id
        String parent_id = existOneSubject.getId();

        // 添加二级分类
        // 判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectData.getOneSubjectName(), parent_id, eduSubjectService);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(parent_id);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(existTwoSubject);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    // 判断一级分类,不能重复添加
    private EduSubject existOneSubject(String name, EduSubjectService eduSubjectService) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }

    // 判断二级分类,不能重复添加
    private EduSubject existTwoSubject(String name, String parent_id, EduSubjectService eduSubjectService) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", parent_id);
        EduSubject twoSubject = eduSubjectService.getOne(wrapper);
        return twoSubject;
    }
}
