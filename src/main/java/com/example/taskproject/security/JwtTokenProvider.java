package com.example.taskproject.security;

import com.example.taskproject.exception.APIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final String SECRET = "shetydhbfjkwieu4662ydhhgdshetydhbfjkwieu4662ydhhgd";

    public String generateToken(Authentication authentication){
        String email = authentication.getName();
        Date currentDate = new Date(); // initializes with Date at the time of creation
        Date expiredDate = new Date(currentDate.getTime() + 3600000);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expiredDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getSignKey(){
        byte[] signKey = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(signKey);
    }

    public boolean isValidToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            throw new APIException("Token Issue: " + e.getMessage());
        }
        return true;
    }

    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmailFromToken(String token){
        Claims jwtTokenClaims = extractAllClaims(token);
        return jwtTokenClaims.getSubject();
    }




}
