package com.hzlei.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzlei.eduservice.entity.EduChapter;
import com.hzlei.eduservice.entity.EduVideo;
import com.hzlei.eduservice.entity.chapter.ChapterVo;
import com.hzlei.eduservice.entity.chapter.VideoVo;
import com.hzlei.eduservice.mapper.EduChapterMapper;
import com.hzlei.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzlei.eduservice.service.EduVideoService;
import com.hzlei.servicebase.exceptionhandler.HzleiException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author hzlei
 * @since 2020-07-05
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Resource
    private EduVideoService videoService;

    /**
     * 课程大纲, 根据课程 id 查询
     * @param courseId 课程id
     * @return
     */
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        // 创建 list, 用于最终封装数据
        List<ChapterVo> finalList = new ArrayList<>();

        // 1. 查询所有章节 by courseId
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> eduChaptersList = baseMapper.selectList(wrapperChapter);

        // 2. 查询所有小节 by courseId
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);

        // 3. 遍历章节 list 集合进行封装
        // 遍历章节 list
        for (EduChapter eduChapter : eduChaptersList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalList.add(chapterVo);

            // 用于封装章节里面的小节
            List<VideoVo> videoList = new ArrayList<>();

            // 4. 遍历小节 list 集合进行封装
            for (EduVideo eduVideo : eduVideoList) {
                // 判断: 章节 id == 小节 chapter_id
                if (eduChapter.getId().equals(eduVideo.getChapterId())) {
                    // 进行封装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    // 放到 小节集合中
                    videoList.add(videoVo);
                }
            }
            // 封装后的小节集合，放到章节对象里面
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }

    /**
     * 删除章节信息
     * @param chapterId 章节 id
     * @return
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        // 根据章节id（chapterId）查询小节，查询到小节，不能删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if (count > 0) throw new HzleiException(20001, "章节里面有小节，不能删除");
        else {
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }

    }
}
