package com.example.api.service;

import com.example.api.entity.User;
import com.example.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * 사용자 서비스 클래스
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String userId, String password, String name, String regNo) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setRegNo(regNo);
        return userRepository.save(user);
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public boolean isValidUser(String name, String regNo) {
        return (name.equals("동탁") && regNo.equals("921108-1582816"))
                || (name.equals("관우") && regNo.equals("681108-1582816"))
                || (name.equals("손권") && regNo.equals("890601-2455116"))
                || (name.equals("유비") && regNo.equals("790411-1656116"))
                || (name.equals("조조") && regNo.equals("810326-2715702"));
    }
}
