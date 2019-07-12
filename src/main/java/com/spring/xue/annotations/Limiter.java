package com.spring.xue.annotations;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 自定义注解
 * target定义当前注释的适用范围
 * Docmented：自定义注解是不被javaDoc 所记录的 只是Docmented就会让javaDoc记录当前接口
 * Retention
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limiter {

    String value() default "";

    /**
     *每秒向桶中放入令牌的数量   默认最大即不做限流
     * */
    double perSecound() default Double.MAX_VALUE;

    int timeOut() default 0;

    TimeUnit timeOutUnit() default  TimeUnit.MILLISECONDS;



}
