package com.hzlei.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 自定义异常
 * @Author hzlei
 * @Date 2020/6/22 21:56
 */
@Data
// 有参构造方法注解
@AllArgsConstructor
// 无参构造方法注解
@NoArgsConstructor
public class HzleiException extends RuntimeException {

    private Integer code;// 状态码
    private String msg;// 异常信息

}
