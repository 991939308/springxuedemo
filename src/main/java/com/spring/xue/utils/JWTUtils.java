package com.spring.xue.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.xue.Bean.JWTResult;
import com.spring.xue.Bean.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
                .setIssuedAt(now);//token生成时间
//                .signWith(secret);//设置密匙
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
/*************************2019年4月12日15:41:05 生成 TOKEN */
    /**
     * 用户登录成功后生成Jwt
     * 使用Hs256算法  私匙使用用户密码
     *
     * @param ttlMillis jwt过期时间
     * @param user      登录成功的user对象
     * @return
     */
    public static String createJWT(long ttlMillis, User user) {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //创建payload的私有声明（开放数据）（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("password", user.getPassword());

        //生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        String key = user.getPassword();
        //生成签发人
        String subject = user.getUsername();
        //下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(UUID.randomUUID().toString())
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            //设置过期时间
            builder.setExpiration(exp);
        }
        return builder.compact();
    }


}
