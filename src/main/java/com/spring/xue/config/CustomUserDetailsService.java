//package com.spring.xue.config;
//
//import com.spring.xue.service.LoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Map;
//
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private LoginService loginService;
//
//    //SUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Map user = loginService.getUserbyName(userName);
//        if(user==null){
//            throw  new UsernameNotFoundException("UserName:"+userName+"not found");
//        }
//        return null;
//    }
//}
