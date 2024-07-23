package com.example.api.dto;

/**
 * 스크래핑 요청 DTO 클래스
 */
public class ScrapingRequest {
    private String name;
    private String regNo;

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
}
