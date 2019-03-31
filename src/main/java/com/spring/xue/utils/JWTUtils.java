package com.spring.xue.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
public class JWTUtils {
    //定义token过期时间
    public static final long EXPIRE_TIME= 50*60*1000;

    /**生成token*/
    public static String sign(String userId,String secret){
        try {
            //设置过期时间
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("userId",userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error("不支持的编码类型异常");
            return null;
        } catch (Exception e){
            log.error("生成token异常");
            return null;
        }
    }

    /**解析token数据*/

//    public static

}
