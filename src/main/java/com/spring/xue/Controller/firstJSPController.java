package com.spring.xue.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class firstJSPController {

    @RequestMapping("/login")
    public String firstview(Model model){
        model.addAttribute("now", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return "login";
    }
}
