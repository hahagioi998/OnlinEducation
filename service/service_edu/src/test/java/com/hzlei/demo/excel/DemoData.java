package com.hzlei.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description: 实体类, 与 excel 对应
 * @Author hzlei
 * @Date 2020/7/2 21:28
 */
@Data
public class DemoData {

    // 设置 excel 表头
    @ExcelProperty(value = "学生编号", index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名", index = 1)
    private String sname;

}
