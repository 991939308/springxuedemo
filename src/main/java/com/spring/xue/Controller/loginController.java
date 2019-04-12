package com.spring.xue.Controller;

import com.spring.xue.Bean.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@CrossOrigin
@Slf4j
public class loginController {


    @RequestMapping("/login.htm")
    public String loginview(){

        return "login.html";

    }
    @RequestMapping("/login.ajax")
    public String loginajax(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            //这里添加shiro的权限校验
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            SecurityUtils.getSubject().login(token);
            return "index.html";

        } catch (AuthenticationException e) {
            log.info("登录错误"+e+"");
            return "error.html";

        }

    }


    @ResponseBody
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody User user) {
        Map<String,Object> resultmap = new HashMap<>();
        //1、获取subject
        Subject subject = SecurityUtils.getSubject();

        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());

        //3、执行登录方法
        try {
            //当认证失败会报异常
            subject.login(token);
            resultmap.put("success",true);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("认证失败");
            resultmap.put("success",false);

        }
        return resultmap;

    }

    @ResponseBody
    @PostMapping("/test")
    public String test(){

        return "test";
    }


}
