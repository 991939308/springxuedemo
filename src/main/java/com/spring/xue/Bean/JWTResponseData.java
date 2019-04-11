package com.spring.xue.Bean;

import lombok.Data;

@Data
public class JWTResponseData {

    private Integer code;
    private Object data;
    private String msg;
    private String token;


}
