package com.hzlei.eduservice.entity.frontvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author hzlei
 * @Date 2020/12/15 21:06
 * @Description: 课程 vo 类
 */
@Data
public class CourseFrontVo {
    @ApiModelProperty("课程名称")
    private String title;

    @ApiModelProperty("讲师 id")
    private String teacherId;

    @ApiModelProperty("一级类别 id")
    private String subjectParentId;

    @ApiModelProperty("二级类别 id")
    private String subjectId;

    @ApiModelProperty("销量排序")
    private String buyCountSort;

    @ApiModelProperty("最新时间排序")
    private String gmtCreateSort;

    @ApiModelProperty("价格排序")
    private String priceSort;
}
