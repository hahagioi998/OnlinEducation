package com.hzlei.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 课程最终发布信息封装类
 * @Author hzlei
 * @Date 2020/7/16 21:04
 */
@Data
public class CoursePublishVo implements Serializable {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}
