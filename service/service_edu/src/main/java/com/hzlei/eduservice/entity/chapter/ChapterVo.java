package com.hzlei.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 课程章节
 * @Author hzlei
 * @Date 2020/7/10 20:58
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    // 章节里面的小节
    private List<VideoVo> children = new ArrayList<>();
}
