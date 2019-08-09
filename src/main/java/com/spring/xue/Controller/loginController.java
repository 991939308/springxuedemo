package com.spring.xue.Controller;

import com.spring.xue.Bean.User;
import com.spring.xue.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@ResponseBody
@RequestMapping("/")
public class loginController {
    @Value("${Service.JWT.SECRTKEY}")
    private String key;
    @Value("${Service.JWT.EXPIRE_TIME}")
    private long EXPIRE_TIME;



    @RequestMapping("/test.ajax")
    public String test(){
        return "test";
    }


    @RequestMapping("/getjwt.ajax")
    public String login(String username,String password){
        User user = new User(username,password);
        String token = JWTUtils.createJWT(EXPIRE_TIME,key,user);
        return token;
    }

}
