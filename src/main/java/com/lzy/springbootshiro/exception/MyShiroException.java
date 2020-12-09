package com.lzy.springbootshiro.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常
 * 2020-11-25
 * lzy
 * @ControllerAdvice 对异常统一处理。拿到这些异常信息后，可以做一些处理，比如提供一个统一的web界面查看异常信息，
 * 或者普通到异常信息后，发送短信、邮件形式通知到相关人员，可以帮助开发人员快速发现并定位问题，
 * 减少以往通过查看线上日志文件排查问繁琐锁耗时的所耗费的时间。
 */
@ControllerAdvice
public class MyShiroException {

    /**
     * 处理shiro权限拦截异常
     * 如果返回JSON数据格式请加上@ResponseBody注解
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Map<String,Object> defaultErrorHandler(){
        Map<String,Object> map = new HashMap<>();
        map.put("403","权限不足");
        return map;
    }
}
