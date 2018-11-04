package com.spring.xue.Controller;

import com.spring.xue.service.firstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class firstController {

    @Autowired
    firstService f;
    /*
     * @program: xue
     * @create: 2018-11-02-11-48
     *
     */
    @RequestMapping("/first")
    public String first(){
        String a = f.fitst();
        System.out.println("first");
        return a;
    }

    @RequestMapping("/fileupload")
    public Map<String,Object> fileUpload(MultipartFile filename) throws IOException {
        System.out.println("1---"+this.getClass().getResource("/")+filename.getOriginalFilename());
        System.out.println("2---"+this.getClass().getResource("/")+filename.getOriginalFilename());
        filename.transferTo(new File(this.getClass().getResource("/").getPath()+filename.getOriginalFilename()));
        HashMap<String,Object> obj = new HashMap<>();
        obj.put("msg",200);
        return obj;
    }
}
