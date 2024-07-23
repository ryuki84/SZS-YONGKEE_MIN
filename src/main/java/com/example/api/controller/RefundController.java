package com.example.api.controller;

import com.example.api.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 환급액 조회 컨트롤러
 */
@RestController
@RequestMapping("/szs")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @GetMapping("/refund")
    public Map<String, Object> getRefund() {
        // Spring Security 컨텍스트에서 인증된 사용자 정보 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId;
        if (principal instanceof UserDetails) {
            userId = ((UserDetails) principal).getUsername();
        } else {
            userId = principal.toString();
        }

        return refundService.calculateRefund(userId);
    }
}
