package com.spring.xue.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟数据库数据--用户
 * */
public class UserUtils {


    public static final Map<String, Object> USERS = new HashMap<>();

    static {
        for (int i = 0; i < 10; i++) {
            USERS.put("admin" + i, "password" + i);
        }
    }

    //判断是否可以登录
    public static boolean isLogin(String username, String password) {
        if(StringUtils.isBlank(username) || username.trim().length()==0){
            return false;
        }
        if(StringUtils.isBlank(password) || password.trim().length()==0){
            return false;
        }
        return true;
    }

}
