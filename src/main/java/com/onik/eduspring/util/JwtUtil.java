package com.onik.eduspring.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {

    // 密钥（建议至少 256 位）
    private static final String SIGN_KEY = "12345678901234567890123456789012"; // 32 字符

    private static final Key KEY = Keys.hmacShaKeyFor(SIGN_KEY.getBytes(StandardCharsets.UTF_8));

    // 过期时间（8小时）
    private static final long EXPIRE_TIME = 8 * 60 * 60 * 1000;
    /**
     * 生成JWT令牌
     * @param claims 存储的数据
     * @return 生成的JWT令牌字符串
     */
    public static String generateToken(Map<String, Object> claims){
        log.info("生成JWT令牌:{}", claims);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param token 要解析的JWT令牌字符串
     * @return 包含令牌信息的Claims对象
     * @throws Exception 如果令牌无效或已过期，则抛出异常
     */
    public static Claims parseToken(String token) throws Exception {
        log.info("解析JWT令牌:{}", token);
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}