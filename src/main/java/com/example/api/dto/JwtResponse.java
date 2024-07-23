package com.example.api.dto;

/**
 * JWT 응답 DTO 클래스
 */
public class JwtResponse {
    private String accessToken;

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
