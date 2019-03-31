package com.spring.xue.Bean;

import lombok.Data;

@Data
public class ResponseBean {

    private int code;
    private String msg;
    private Object data;
}