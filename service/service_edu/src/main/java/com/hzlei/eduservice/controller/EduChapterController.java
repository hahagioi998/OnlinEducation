package com.hzlei.eduservice.controller;


import com.hzlei.commonutils.R;
import com.hzlei.eduservice.entity.chapter.ChapterVo;
import com.hzlei.eduservice.service.EduChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程章节表, 存储课程章节信息 前端控制器
 * </p>
 *
 * @author hzlei
 * @since 2020-07-05
 */
// 解决跨域
@CrossOrigin
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Resource
    private EduChapterService chapterService;

    /**
     * 课程大纲, 根据课程 id 查询
     * @param courseId 课程id
     * @return
     */
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo", list);
    }

}

