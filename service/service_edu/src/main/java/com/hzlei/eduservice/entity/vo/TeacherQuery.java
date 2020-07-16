package com.hzlei.eduservice.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 模糊查询数据封装
 * @Author hzlei
 * @Date 2020/6/18 21:19
 */
@Data
public class TeacherQuery implements Serializable {

    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 00:00:00")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 00:00:00")
    private String end;
}
