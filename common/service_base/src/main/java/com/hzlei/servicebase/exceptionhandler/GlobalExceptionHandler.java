package com.hzlei.servicebase.exceptionhandler;

import com.hzlei.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 统一异常处理类
 * @Author hzlei
 * @Date 2020/6/22 21:20
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    // 指定出现什么异常执行此方法
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("异常处理: " + e);
    }
}
