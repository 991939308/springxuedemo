package com.spring.xue.service.Impl;

import com.spring.xue.Bean.User;
import com.spring.xue.mapper.LoginMapper;
import com.spring.xue.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public Map<String,Object> getUserbyName(String username) {
        return loginMapper.getUserbyName(username);
    }
}
