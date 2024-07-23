package com.example.api.repository;

import com.example.api.entity.ScrapingData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * 스크래핑 데이터 레포지토리 인터페이스
 */
public interface ScrapingDataRepository extends JpaRepository<ScrapingData, UUID> {
    ScrapingData findByUserId(String userId);
}
