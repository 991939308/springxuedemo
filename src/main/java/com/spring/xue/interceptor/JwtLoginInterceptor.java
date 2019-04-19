package com.spring.xue.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.spring.xue.service.LoginService;
import com.spring.xue.utils.JWTUtils;
import com.spring.xue.utils.StringUtils;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * JWT登录拦截器
 * */
@Slf4j
public class JwtLoginInterceptor implements HandlerInterceptor {
    @Value("${Service.JWT.SECRTKEY}")
    private String key;


    @Autowired
    private LoginService loginService;
/**
 * 接收到请求但方法还没有处理请求之前
 * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求头jwtToken
        String token  = request.getHeader("jwttoken");
        if(StringUtils.isBlank(token)){
            throw new RuntimeException("无token访问,请重新登录");
        }else{//有token判断当前token中的用户信息
            try {
                String username = JWT.decode(token).getClaim("sub").asString();
                Map userDto = loginService.getUserbyName(username);
                if(userDto.isEmpty()){
                    throw new RuntimeException("当前用户不存在，请重新登录");
                }
                boolean isVerify = JWTUtils.isVerify(token,userDto,key);
                if(!isVerify){
                    throw new RuntimeException("非法访问");
                }
            } catch (JWTDecodeException e) {
                log.error("jwt解析异常"+e);
            }



        }

        if(StringUtils.isBlank(token)){
            return  false;
        }else{//校验jwttoken
            return true;
        }

//        return true;
    }

    /**
     * 处理完请求后 但是没好友渲染Response之前
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 方法处理完请求 返回Response之前
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
