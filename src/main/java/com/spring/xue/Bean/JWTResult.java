package com.spring.xue.Bean;

import io.jsonwebtoken.Claims;
import lombok.Data;

/*jwt的结果的对象*/
@Data
public class JWTResult {

    private int errCode;
    private String success;
    /*
    * 验证过程中的payload中得数据对象
    * */
    private Claims claims;


}
