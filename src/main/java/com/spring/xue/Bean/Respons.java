package com.spring.xue.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Respons {
    private int code;
    private boolean success;
    private String msg;
    private Object data;
    private String token;



}
