package com.example.api.service;

import com.example.api.entity.ScrapingData;
import com.example.api.repository.ScrapingDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 스크래핑 서비스 클래스
 */
@Service
public class ScrapingService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ScrapingDataRepository scrapingDataRepository;

    public Map<String, Object> scrapeIncome(String userId, String name, String regNo) {
        String url = "https://codetest-v4.3o3.co.kr/scrap";
        String apiKey = "+ZrsfXOAgBaMjNuRarUfgg==";

        // 요청 바디 생성
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("regNo", regNo);

        // 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-Key", apiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        // API 호출
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        Map<String, Object> responseBody = response.getBody();

        if (responseBody == null) {
            throw new IllegalStateException("Response body is null");
        }

        // 응답 본문을 로그로 출력하여 확인
        System.out.println("Response Body: " + responseBody);

        Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
        if (data == null) {
            throw new IllegalStateException("Data is missing");
        }

        Long totalIncome = parseLong(data.get("종합소득금액"));
        if (totalIncome == null) {
            throw new IllegalStateException("Total income is missing");
        }

        Map<String, Object> deductions = (Map<String, Object>) data.get("소득공제");
        if (deductions == null) {
            throw new IllegalStateException("Deductions are missing");
        }

        // 국민연금 공제 합산
        Long pensionDeduction = 0L;
        List<Map<String, Object>> pensionList = (List<Map<String, Object>>) deductions.get("국민연금");
        if (pensionList != null) {
            for (Map<String, Object> item : pensionList) {
                pensionDeduction += parseLong(item.get("공제액"));
            }
        }

        // 신용카드 공제 합산
        Long creditCardDeduction = 0L;
        Map<String, Object> creditCardDeductions = (Map<String, Object>) deductions.get("신용카드소득공제");
        if (creditCardDeductions != null) {
            List<Map<String, Object>> monthList = (List<Map<String, Object>>) creditCardDeductions.get("month");
            if (monthList != null) {
                for (Map<String, Object> item : monthList) {
                    creditCardDeduction += parseLong(item.values().iterator().next());
                }
            }
        }

        // 세액공제
        Long taxDeduction = parseLong(deductions.get("세액공제"));
        if (taxDeduction == null) {
            throw new IllegalStateException("Tax deduction is missing");
        }

        // 데이터베이스에 저장
        ScrapingData scrapingData = new ScrapingData();
        scrapingData.setUserId(userId);
        scrapingData.setName(name);
        scrapingData.setRegNo(regNo);
        scrapingData.setTotalIncome(totalIncome);
        scrapingData.setPensionDeduction(pensionDeduction);
        scrapingData.setCreditCardDeduction(creditCardDeduction);
        scrapingData.setTaxDeduction(taxDeduction);

        scrapingDataRepository.save(scrapingData);

        return responseBody;
    }

    private Long parseLong(Object value) {
        if (value == null) {
            return 0L;
        }
        try {
            return Long.parseLong(value.toString().replace(",", ""));
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}
