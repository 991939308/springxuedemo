package com.spring.xue.springcomponent;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈跨域拦截器〉
 *
 * @author Administrator
 * @create 2019-01-08,0008
 * @since 1.0.0
 */
@Component
public class CommonIntercepter implements HandlerInterceptor {

    /**
     * 跨域拦截器
     *
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //允许跨域,不能放在postHandle内
        System.out.println("触发拦截器");
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (request.getMethod().equals("OPTIONS")) {
            response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");
        }
        return true;
    }
}
