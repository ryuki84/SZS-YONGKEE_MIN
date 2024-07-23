package com.example.api.dto;

/**
 * 로그인 요청 DTO 클래스
 */
public class LoginRequest {
    private String userId;
    private String password;

    // getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
