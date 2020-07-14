package com.hzlei.servicebase.exceptionhandler;

import com.hzlei.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 统一异常处理类
 * @Author hzlei
 * @Date 2020/6/22 21:20
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    // 指定出现什么异常执行此方法
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        log.error(String.valueOf(e));
        e.printStackTrace();
        return R.error().message("全局异常处理: " + e.getStackTrace()[0]);
    }

    @ResponseBody
    // 指定出现什么异常执行此方法
    @ExceptionHandler(ArithmeticException.class)
    public R error(ArithmeticException ae) {
        log.error(String.valueOf(ae));
        ae.printStackTrace();
        return R.error().message("特定异常处理: " + ae.getStackTrace()[0]);
    }

    @ResponseBody
    // 指定出现什么异常执行此方法
    @ExceptionHandler(HzleiException.class)
    public R error(HzleiException he) {
        log.error(String.valueOf(he));
        he.printStackTrace();
        return R.error().code(he.getCode()).message(he.getMsg());
    }
}
