package com.example.taskproject.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
public class AuthTokenResponse {
    private final String token;
    private final String tokenType = "Bearer";

    public AuthTokenResponse(String token){
        this.token = token;
    }


}
