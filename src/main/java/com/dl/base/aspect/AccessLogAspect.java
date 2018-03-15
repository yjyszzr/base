package com.dl.base.aspect;

import com.google.common.collect.Lists;
import com.dl.base.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 接入日志拦截器
 *
 * @create 2017/12/27 11:29
 **/
@Slf4j
@Aspect
public class AccessLogAspect {

    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("执行方法为：{}， 参数列表为：{}, 当前用户为：{}, 当前地址为：{}", joinPoint.toShortString(),
                Lists.newArrayList(args), SessionUtil.getUserId(), SessionUtil.getAddress());
    }
}
