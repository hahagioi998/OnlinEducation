package com.hzlei.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO:
 * @Author hzlei
 * @Date 2020/7/2 21:31
 */
public class TestEasyExcel {

    String fileName = "C:/Users/hzlei/Desktop/write.xlsx";

    // 写操作
    @Test
    public void write() {
        // 实现 excel 写的操作
        // 1, 设置写入文件夹地址和 excel 文件名称
//        String fileName = "C:/Users/hzlei/Desktop/write.xlsx";

        /*
         * 2, 调用 easyexcel 里面的方法实现写操作
         *
         * param1: 文件路径名称
         * param2: 实体类 class
         */
        EasyExcel.write(fileName, DemoData.class)
                .sheet("学生列表")
                .doWrite(getData());

    }

    // 读操作
    @Test
    public void read() {
        EasyExcel.read(fileName, DemoData.class, new ExcelListener())
                .sheet().doRead();
    }




    // 创建方法返回 list 数据集合
    public static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("张三 --> " + i);
            list.add(data);
        }
        return list;
    }


}
