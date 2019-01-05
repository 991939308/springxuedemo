package com.spring.xue.config;

//    shiro 配置类

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义域
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行授权逻辑");
        return null;
    }

    /**
     * 执行认证的逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("执行认证的逻辑");
        String name = "admin";
        String password = "password";

        //1、判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        if(!name.equals(token.getUsername())){
            //返回nullshiro会抛出一个UnknowAccountException
            return null;
        }
        //2、判断密码-密码不需要我自己判断shiro会自己判断

        log.info("开始判断密码");
        //第一个是返回logcontroller的信息，第二个数据必须是数据库的密码
        return new SimpleAuthenticationInfo("",password,"");
    }
}
