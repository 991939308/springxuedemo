package com.spring.xue.Controller;

import com.spring.xue.Bean.Respons;
import com.spring.xue.Bean.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.spring.xue.utils.JWTUtils.createJWT;

@Slf4j
@Controller
@ResponseBody
@RequestMapping("/")
public class loginController {

    @RequestMapping("/login")
    public Object login(@Valid User user) {
//        Map<String,Object> rust = new HashMap<>();
        //1、获取subject
        Subject subject = SecurityUtils.getSubject();

        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        //3、执行登录方法
        String JwtToken ="";
        try {
            //当认证失败会报异常
            subject.login(token);
            //设置过期时间
            JwtToken = createJWT(1800000,user);//30分钟有效期
            log.info("当前token:"+JwtToken);
            return new Respons(200,true,"成功",null,JwtToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("认证失败");
            return new Respons(100,false,"失败",null,"");
        }


    }


}
