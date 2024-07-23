package com.example.api.repository;

import com.example.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * 사용자 레포지토리 인터페이스
 */
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserId(String userId);
}
