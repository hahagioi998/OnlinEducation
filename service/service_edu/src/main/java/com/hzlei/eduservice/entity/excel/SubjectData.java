package com.hzlei.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description: excel 实体类
 * @Author hzlei
 * @Date 2020/7/2 22:40
 */
@Data
public class SubjectData {

    // 一级分类
    @ExcelProperty(value = "课程分类", index = 0)
    private String oneSubjectName;

    // 二级分类
    @ExcelProperty(value = "课程名称", index = 1)
    private String twoSubjectName;


}
