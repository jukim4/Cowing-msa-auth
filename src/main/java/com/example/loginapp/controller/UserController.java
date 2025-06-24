package com.example.loginapp.controller;

import com.example.loginapp.entity.UserEntity;
import com.example.loginapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String email,
            @RequestParam String passwd,
            @RequestParam String nickname) {

        try {
            userService.registerUser(email, passwd, nickname);
            return ResponseEntity.ok("회원가입 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("회원가입 실패: " + e.getMessage());
        }
    }
}