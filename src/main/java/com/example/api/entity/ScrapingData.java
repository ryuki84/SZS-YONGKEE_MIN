package com.example.api.entity;

import jakarta.persistence.*;
import java.util.UUID;

/**
 * 스크래핑 데이터 엔티티 클래스
 */
@Entity
@Table(name = "scraping_data")
public class ScrapingData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String userId;
    private String name;
    private String regNo;
    private Long totalIncome;
    private Long pensionDeduction;
    private Long creditCardDeduction;
    private Long taxDeduction;

    // getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public Long getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Long totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Long getPensionDeduction() {
        return pensionDeduction;
    }

    public void setPensionDeduction(Long pensionDeduction) {
        this.pensionDeduction = pensionDeduction;
    }

    public Long getCreditCardDeduction() {
        return creditCardDeduction;
    }

    public void setCreditCardDeduction(Long creditCardDeduction) {
        this.creditCardDeduction = creditCardDeduction;
    }

    public Long getTaxDeduction() {
        return taxDeduction;
    }

    public void setTaxDeduction(Long taxDeduction) {
        this.taxDeduction = taxDeduction;
    }
}
