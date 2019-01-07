package com.spring.xue.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
@RequestMapping("/")
public class loginController {

    @RequestMapping("/login")
    public String login(String name, String password) {

        //1、获取subject
        Subject subject = SecurityUtils.getSubject();

        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        //3、执行登录方法
        try {
            //当认证失败会报异常
            subject.login(token);
            return "true";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("认证失败");
            return "false";
        }


    }


}
