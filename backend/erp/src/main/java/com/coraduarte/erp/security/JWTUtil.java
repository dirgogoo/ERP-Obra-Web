package com.coraduarte.erp.security;

import java.util.Date;
import java.util.Objects;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getKeybySecretKey(){
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return key;
    }

    private Claims getClaims(String token){
        SecretKey key = getKeybySecretKey();
        try{
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        }catch (Exception e){
            return null;
        }
    }


    public String generateToken(String username){
        SecretKey key = getKeybySecretKey();
        return Jwts.builder()
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + this.expiration))
        .signWith(key)
        .compact();
    }

    public boolean isValidToken(String token){
        Claims claims = getClaims(token);

        if (Objects.nonNull(claims)){
            String username = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (Objects.nonNull(username) && Objects.nonNull(expiration) && now.before(expiration))
            return true;
        }
        return false;
    }

}