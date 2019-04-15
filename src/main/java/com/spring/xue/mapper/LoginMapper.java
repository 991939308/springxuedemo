package com.spring.xue.mapper;

import java.util.Map;

public interface LoginMapper {

    Map<String, Object> getUserbyName(String username);

}
