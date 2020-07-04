package com.hzlei.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzlei.eduservice.entity.EduSubject;
import com.hzlei.eduservice.entity.excel.SubjectData;
import com.hzlei.eduservice.entity.tree.OneSubject;
import com.hzlei.eduservice.entity.tree.TwoSubject;
import com.hzlei.eduservice.listener.SubjectExcelListener;
import com.hzlei.eduservice.mapper.EduSubjectMapper;
import com.hzlei.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author hzlei
 * @since 2020-07-02
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    // 添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {

        try {
            // 文件输入流
            InputStream in = file.getInputStream();

            // 调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 课程分类列表
    @Override
    public List<OneSubject> getAllSubject() {
        /*
         * 分析:
         *
         * 1. 针对返回的树形结构数据, 创建对应的实体类: OneSubject, TwoSubject
         * 2. 在实体类之间表示关系(一个一级分类中有多个二级分类)
         * 3. 封装数据
         */

        // 最终数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        // 1. 查询所有一级分类 parent_id = 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        // 2. 查询所有二级分类 parent_id != 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        // 3. 封装一级分类
        for (EduSubject eduSubject : oneSubjectList) {
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            BeanUtils.copyProperties(eduSubject, oneSubject);

            finalSubjectList.add(oneSubject);

            // 4. 封装二级分类
            List<TwoSubject> children = new ArrayList<>();
            for (EduSubject two : twoSubjectList) {
                if (two.getParentId().equals(eduSubject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(two, twoSubject);
                    children.add(twoSubject);
                }
            }

            oneSubject.setChildren(children);
            
        }


        

        // 返回数据
        return finalSubjectList;

    }




}
