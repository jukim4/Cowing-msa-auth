package cowing.auth.controller;

import cowing.auth.dto.RegisterUserDto;
import cowing.auth.entity.User;
import cowing.auth.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto dto) {
        boolean result = userService.registerUser(dto.email(), dto.passwd(), dto.nickname(), dto.username());
        if (result) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 201);
            response.put("message", "회원가입에 성공하였습니다. 축하드립니다!");
            return ResponseEntity.status(201).body(response);
        } else {
            return ResponseEntity.badRequest().body("회원가입 실패");
        }
    }

    // @PostMapping("/signin")
    // public ResponseEntity<?> signin(
    // @RequestParam String email,
    // @RequestParam String passwd) {

    // return userService.loginAndReturnUser(email, passwd)
    // .map(ResponseEntity::ok)
    // .orElseGet(() -> ResponseEntity.status(401).body(null));
    // }

    // @PutMapping("/change/password")
    // public ResponseEntity<String> changePassword(
    // @RequestParam String email,
    // @RequestParam String currentPwd,
    // @RequestParam String newPwd) {

    // boolean result = userService.updatePassword(email, currentPwd, newPwd);

    // if (result) {
    // return ResponseEntity.ok("비밀번호 변경 성공");
    // } else {
    // return ResponseEntity.status(401).body("현재 비밀번호가 일치하지 않거나 사용자 없음");
    // }
    // }
}