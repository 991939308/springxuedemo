package com.spring.xue.utils;

import java.util.UUID;

public class StringUtils {
    public static String getUUID32(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }
}
