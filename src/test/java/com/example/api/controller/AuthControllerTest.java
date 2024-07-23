package com.example.api.controller;

import com.example.api.entity.User;
import com.example.api.service.UserService;
import com.example.api.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

/**
 * AuthController 테스트 클래스
 */
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User();
        user.setUserId("kw68");
        user.setPassword("123456");
        user.setName("관우");
        user.setRegNo("681108-1582816");

        Mockito.when(userService.isValidUser(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        Mockito.when(userService.registerUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(user);

        mockMvc.perform(post("/szs/signup")
                .contentType("application/json")
                .content("{\"userId\":\"kw68\",\"password\":\"123456\",\"name\":\"관우\",\"regNo\":\"681108-1582816\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is("kw68")));
    }

    @Test
    public void testAuthenticateUser() throws Exception {
        User user = new User();
        user.setUserId("kw68");
        user.setPassword("123456");

        String token = "dummy-jwt-token";

        Mockito.when(jwtUtil.generateToken(Mockito.anyString())).thenReturn(token);

        mockMvc.perform(post("/szs/login")
                .contentType("application/json")
                .content("{\"userId\":\"kw68\",\"password\":\"123456\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", is(token)));
    }
}
