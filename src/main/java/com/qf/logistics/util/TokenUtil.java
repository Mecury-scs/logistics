package com.qf.logistics.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @author Galaxy
 */
public class TokenUtil {
    /**
     * token有效期
     */
    private static Long tokenExpireTime = 1000 * 60 * 3000L;
    /**
     * 公私钥
     */
    public static final String PRIVATEKEY = "privateKey";
    public static final String ACCESSTOKEN = "AccessToken";
    /**
     * 密钥
     */
    private static final String secretKey = "ueor82739sjsd234759jdfijosd289347sdjklfvjsxdr389wrksjdhfjksdyr9234yu89htsdkhfjksdhf83wy4hrsdjkhfsdjkh8i34wyuirfhsdjkfsxmnfbcvm,xcnskdhfriw3eyrikni12y391y238923y4y89dfhisfhsdjknfk23w4y598hfdjkfkjxcfbnisyer93we5rhkdjsfnjks";

    /**
     * 生成token
     *
     * @param userName
     * @return
     */
    public static String createToken(String userName) {
        // 得到当前时间
        Date now = new Date();
        // 通过hs256算法，以及secretKey得到Algorithm对象
        Algorithm algo = Algorithm.HMAC256(secretKey);
        String token = JWT.create()
                .withIssuer("qianfeng")
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + tokenExpireTime))
                //保存身份标识
                .withClaim("userName", userName)
                .sign(algo);
        return token;
    }


    /**
     * JWT验证
     *
     * @param token
     * @return userName
     */
    public static String verifyJWT(String token) {
        String userName = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("qianfeng")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            userName = jwt.getClaim("userName").asString();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return userName;
    }

    public static void main(String[] args) {
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJxaWFuZmVuZyIsImV4cCI6MTY3Mjk4ODgwNywidXNlck5hbWUiOiJhZG1pbjEiLCJpYXQiOjE2NzI5ODcwMDd9.FSCJV0CzB_Zhw9dG6kFkCh9efzxBa2WXeEjuP_UzXiA";
        System.out.println(verifyJWT(jwt));

//        System.out.println(createToken("admin"));
    }
}
