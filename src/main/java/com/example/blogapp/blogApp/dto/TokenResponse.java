package com.example.blogapp.blogApp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private UserDto user;

    public TokenResponse(String accessToken, String refreshToken, UserDto user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }
}
