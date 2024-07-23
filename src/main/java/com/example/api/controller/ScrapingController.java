package com.example.api.controller;

import com.example.api.dto.ScrapingRequest;
import com.example.api.service.ScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * 스크래핑 관련 컨트롤러
 */
@RestController
@RequestMapping("/szs")
public class ScrapingController {

    @Autowired
    private ScrapingService scrapingService;

    @PostMapping("/scrap")
    public ResponseEntity<?> scrapeIncome(@RequestBody ScrapingRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return ResponseEntity.ok(scrapingService.scrapeIncome(username, request.getName(), request.getRegNo()));
    }
}
