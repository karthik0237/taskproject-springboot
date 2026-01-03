package com.example.taskproject.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication){
        String email = authentication.getName();
        Date currentDate = new Date(); // initializes with Date at the time of creation
        Date expiredDate = new Date(currentDate.getTime() + 3600000);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, "JwtSecretKey")
                .compact();
    }


}
