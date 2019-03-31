package com.spring.xue.Bean;

/**自定义异常类*/
public class CustomExceptions extends RuntimeException {

    public CustomExceptions(String msg){
        super(msg);
    }

    public CustomExceptions(){
        super();
    }

}
