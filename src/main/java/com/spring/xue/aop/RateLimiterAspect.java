package com.spring.xue.aop;


import com.google.common.util.concurrent.RateLimiter;
import com.spring.xue.Bean.Respons;
import com.spring.xue.annotations.Limiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;


@Aspect
@Component
@Slf4j
public class RateLimiterAspect {

    private RateLimiter rateLimiter = RateLimiter.create(Double.MAX_VALUE);

    /**
     * 定义一个切点
     * */
    @Pointcut("@annotation(com.spring.xue.annotations.Limiter)")
    public void checkPointcut() {
    }

    @ResponseBody
    @Around(value="checkPointcut()")
    public Object aroundNotice(ProceedingJoinPoint pjp) throws Throwable{//围绕通知
        log.info("拦截到了{}方法。。。",pjp.getSignature().getName());
        //获取目标方法
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        if(targetMethod.isAnnotationPresent(Limiter.class)){
            //获取目标方法@Limiter注解-获得目标对象
            Limiter limiter = targetMethod.getAnnotation(Limiter.class);
            rateLimiter.setRate(limiter.perSecound());
            if(!rateLimiter.tryAcquire(limiter.timeOut(),limiter.timeOutUnit())){
                log.info("rateLimiter lock 未获得锁");
                return Respons.error(false);
            }
        }
        return pjp.proceed();
    }
}
