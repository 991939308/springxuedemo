package com.spring.xue.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FirstAop {

    /*
     * @program: xue
     * @create: 2018-11-02-13-27
     *
     */
    @Pointcut("execution(* com.spring.xue.service.*.*(..))")
    public void beforaspectj(){}


    @Before("beforaspectj()")
    public  void before(){
        System.out.println("前置aaspectj切点");
    }



}
