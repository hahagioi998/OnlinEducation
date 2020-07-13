package com.hzlei.eduservice.service;

import com.hzlei.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzlei.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author hzlei
 * @since 2020-07-05
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
}
