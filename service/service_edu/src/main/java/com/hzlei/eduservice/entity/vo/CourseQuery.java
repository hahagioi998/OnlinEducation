package com.hzlei.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 课程列表查询对象
 * @Author hzlei
 * @Date 2020/7/18 16:50
 */
@Data
public class CourseQuery {

    @ApiModelProperty(value = "课程名称,模糊查询")
    private String title;

    @ApiModelProperty(value = "课程发布状态 已发布 Draft, 未发布 Normal")
    private String status;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 00:00:00")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 00:00:00")
    private String end;

}
