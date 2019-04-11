package com.spring.xue.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.xue.Bean.JWTResult;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
public class JWTUtils {
    //定义token过期时间
    public static final long EXPIRE_TIME = 50 * 60 * 1000;
    /*token口令*/
    public static final String JWT_SECERT = "JWT_TEST_TOKEN";
    //这个是用户Jason对象和字符串的双向转换的
    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static final int JWT_ERRCODE_EXPIRE = 1005;//token过期
    public static final int JWT_ERRCODEFALL = 1006;//token验证失败

    /**
     * 生成token签发JWT
     * id 当前的jwt的唯一性id防止重放攻击
     * iss 签发人
     * subject 是当前jwt面对用户 payload 中得公开得数据  当前用户的登录名
     * ttMillis 有效时间单位毫秒
     * token token是一次性的，是为了一个用户有效时间登录周期内准备的一个token 用户推出token失效
     */
    public static String sign(String id, String iss, String subject, long ttMillis) {
        //生成一个加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //当前时间
        long nowMillis = System.currentTimeMillis();
        //当前时间的日期对象
        Date now = new Date(nowMillis);
        SecretKey secret = generalKey();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)//客户端的唯一标记 可以是客户端的IP 可以是用户ID 可以是服务器生成的ID
                .setIssuer(iss)//设置创建人
                .setSubject(subject)//用户信息
                .setIssuedAt(now)//token生成时间
                .signWith(secret);//设置密匙
        if (ttMillis >= 0) {
            long expMillis = nowMillis+ttMillis;
            Date expDate = new Date(expMillis);
            jwtBuilder.setIssuedAt(expDate);
        }
        return jwtBuilder.compact();

    }

    public static JWTResult validdateJwt(){

        return null;
    }
    /**
     * 创建一个secret的key
     */
    public static SecretKey generalKey() {

        try {
            //这里也可以用base64加密作为key
            byte[] encodeKey = JWT_SECERT.getBytes("UTF-8");
            SecretKey key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
            return key;
        } catch (UnsupportedEncodingException e) {
            log.error("generalKey：生成一个Secret错误");
            return null;
        }
    }

}
