package com.example.api.service;

import com.example.api.entity.ScrapingData;
import com.example.api.repository.ScrapingDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 환급액 계산 서비스
 */
@Service
public class RefundService {

    @Autowired
    private ScrapingDataRepository scrapingDataRepository;

    public Map<String, Object> calculateRefund(String userId) {
        // 데이터베이스에서 스크래핑 데이터 가져오기
        ScrapingData data = scrapingDataRepository.findByUserId(userId);
        if (data == null) {
            throw new IllegalStateException("No data found for user: " + userId);
        }

        // 계산 로직
        long totalIncome = data.getTotalIncome();	// 종합소득금액
        long pensionDeduction = data.getPensionDeduction();	//  국민연금 공제
        long creditCardDeduction = data.getCreditCardDeduction();	// 신용카드 공제
        long taxDeduction = data.getTaxDeduction();	//  세액공제

        long taxableIncome = totalIncome - (pensionDeduction + creditCardDeduction); // 과세표준= 종합소득세 - 소득공제(국민연금+신용카드)
        long tax = calculateTax(taxableIncome);	// 산출세액
        long finalTax = tax - taxDeduction;	// 결정세액 = 산출세액 - 세엑공제

        Map<String, Object> result = new HashMap<>();
        result.put("결정세액", String.format("%,d", finalTax));
        return result;
    }

    //  산출세액 계산 로직
    private long calculateTax(long taxableIncome) {
        // 산출세액 = 과세표준 * 기본세율
        if (taxableIncome <= 14000000) {
            return (long) (taxableIncome * 0.06);
        } else if (taxableIncome <= 50000000) {
            return 840000 + (long) ((taxableIncome - 14000000) * 0.15);
        } else if (taxableIncome <= 88000000) {
            return 6240000 + (long) ((taxableIncome - 50000000) * 0.24);
        } else if (taxableIncome <= 150000000) {
            return 15360000 + (long) ((taxableIncome - 88000000) * 0.35);
        } else if (taxableIncome <= 300000000) {
            return 37060000 + (long) ((taxableIncome - 150000000) * 0.38);
        } else if (taxableIncome <= 500000000) {
            return 94060000 + (long) ((taxableIncome - 300000000) * 0.40);
        } else if (taxableIncome <= 1000000000) {
            return 174060000 + (long) ((taxableIncome - 500000000) * 0.42);
        } else {
            return 384060000 + (long) ((taxableIncome - 1000000000) * 0.45);
        }
    }
}
