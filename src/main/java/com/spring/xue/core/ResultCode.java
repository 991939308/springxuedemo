package com.spring.xue.core;

import lombok.AllArgsConstructor;



@AllArgsConstructor
public enum ResultCode {

    SUCCESS(200,"success"),
    ERROR(-100,"error");


    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
