package com.spring.xue.service;


import com.spring.xue.Bean.User;

import java.util.Map;

public interface LoginService {

    Map<String,Object> getUserbyName(String username);
}
