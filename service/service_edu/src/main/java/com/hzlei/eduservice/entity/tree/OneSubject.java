package com.hzlei.eduservice.entity.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 树形数据: 一级分类
 * @Author hzlei
 * @Date 2020/7/4 17:11
 */
@Data
public class OneSubject {
    private String id;
    private String title;

    // 一个一级分类中有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
