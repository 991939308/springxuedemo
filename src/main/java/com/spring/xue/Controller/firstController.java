package com.spring.xue.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class firstController {

    /*
     * @program: xue
     * @create: 2018-11-02-11-48
     *
     */
    @RequestMapping("/first")
    @ResponseBody
    public String first(){
        System.out.println("first");
        return "first";
    }
}
