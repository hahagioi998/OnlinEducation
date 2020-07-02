package com.hzlei.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Description: 监听器
 * @Author hzlei
 * @Date 2020/7/2 21:51
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {

    // 一行一行读取 excel 内容
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println("==----> " + data);
    }

    // 读取表头
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头: " + headMap);
    }

    // 读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
