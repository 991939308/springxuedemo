package com.spring.xue.Bean;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回结果
 */
@Setter
@Getter
@AllArgsConstructor
@Data
public class Respons {
    private Integer code = 200;
    private Boolean success = true;
    private Integer error;
    private String msg = "success";
    private Object data;
    private String token;

    public Respons(Boolean success, String msg, String token) {
        this.success = success;
        this.msg = msg;
        this.token = token;
    }

    public Respons(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Respons(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public Respons(Boolean success) {
        this.success = success;
    }

    public static Respons error(Boolean bol , String msg){
        return new Respons(bol,msg);
    }

    public static Respons error(Boolean bol){
        return new Respons(bol);
    }
}
