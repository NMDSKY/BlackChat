package com.kkuil.blackchat.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

/**
 * @Author Kkuil
 * @Date 2023/08/05 12:30
 * @Description Jwt工具类
 */
public class JwtUtil {
    /**
     * @param data Map<String, Object>
     * @return String
     * @description 生成token
     */
    public static String create(Map<String, Object> data, String secret, long ttl) {
        try {
            return Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .setClaims(data)
                    .setExpiration(new Date(System.currentTimeMillis() + ttl))
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析token
     *
     * @param token  token
     * @param secret 密钥
     * @return 解析结果
     */
    public static Claims parse(String token, String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}