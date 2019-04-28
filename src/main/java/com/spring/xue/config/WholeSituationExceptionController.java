package com.spring.xue.config;

import com.spring.xue.Bean.Respons;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常捕获处理
 */
@Slf4j
@RestControllerAdvice
public class WholeSituationExceptionController {

    /**
     * 捕获shiro异常errorCode:401 非法请求
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Respons Handle401(ShiroException e) {
        log.error("shrio异常：" + e);
        return new Respons(401, false, "shiro异常", null, null);
    }

    /**
     * 捕获UnauthorizedException 异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Respons Handle401(UnauthorizedException e) {
        log.error("非法请求异常：" + e);
        return new Respons(401, false, "非法请求异常", null, null);
    }

    /**
     * 捕获其他所有异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Respons globalException(HttpServletRequest request, Throwable ex) {
        return new Respons(401, false, "其他异常", null, null);
    }
//    Integer statuus = request.getAttribute("javax.servlet.error.status_code");

    /**
     * 获取Request请求码
     */
    public HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;//服务端错误
        }

        return HttpStatus.valueOf(statusCode);
    }


}
