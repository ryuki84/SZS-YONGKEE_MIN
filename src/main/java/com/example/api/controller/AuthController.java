package com.example.api.controller;

import com.example.api.dto.ApiResponse;
import com.example.api.dto.JwtResponse;
import com.example.api.dto.LoginRequest;
import com.example.api.entity.User;
import com.example.api.service.UserService;
import com.example.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/szs")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (!userService.isValidUser(user.getName(), user.getRegNo())) {
            return ResponseEntity.badRequest().body(new ApiResponse(400, "Invalid user details"));
        }
        userService.registerUser(user.getUserId(), user.getPassword(), user.getName(), user.getRegNo());
        return ResponseEntity.ok(new ApiResponse(200, "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

}
