package com.spring.xue.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * jwt过滤器
 * */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 登入认证
    * */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("进入验证"+request+"==="+mappedValue);
        return true;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        log.info("执行登录"+request+"==="+response);

        return true;
    }
///**
// * 对跨越进行支持
// * */
//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        return true;
//    }
}
