package com.aaa.lee.repast.aspect;

import com.aaa.lee.repast.service.IRepastService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

/**
 * token验证切面
 */
@Component
@Slf4j
@Aspect
public class TokenAspect {
    @Autowired
    private IRepastService repastService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Pointcut("@annotation(com.aaa.lee.repast.annotation.TokenAnnotation)")
    public void pointcut() {
        // TODO nothing todo
    }

    @Before("pointcut()")
    public void before() {
        // 获取Request对象
        String token = httpServletRequest.getParameter("token");
        if (null == token) {
            throw new RuntimeException();
        }
    }
}
