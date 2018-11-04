package com.spring.xue.service;

import com.spring.xue.service.firstService;
import org.springframework.stereotype.Service;

@Service
public class firstServiceImpl implements firstService {

    @Override
    public String fitst() {
        System.out.println("service执行");
        return "service执行";
    }
}
