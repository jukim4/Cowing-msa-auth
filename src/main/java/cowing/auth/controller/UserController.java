package cowing.auth.controller;

import cowing.auth.dto.PasswordChangeDto;
import cowing.auth.dto.PortfolioDto;
import cowing.auth.dto.RegisterUserDto;
import cowing.auth.dto.UserInfoDto;
import cowing.auth.entity.PrincipalDetails;
import cowing.auth.entity.User;
import cowing.auth.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
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

    // 비밀번호 변경
    @PostMapping("/change/passwd")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDto dto) {
        boolean result = userService.updatePassword(dto.email(), dto.currentPwd(), dto.newPwd());
        if (result) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 201);
            response.put("message", "비밀번호가 변경되었습니다!");
            return ResponseEntity.status(201).body(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 400);
            response.put("message", "현재 비밀번호 혹은 이메일이 잘못되었습니다.");
            return ResponseEntity.status(400).body(response);
        }
    }

    // 유저 포트폴리오 조회
    @GetMapping("/portfolio")
    public ResponseEntity<?> getPortfolio(@AuthenticationPrincipal PrincipalDetails principal) {
        String username = principal.getUsername();
        List<PortfolioDto> portfolios = userService.getPortfolio(username);

        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        if (!portfolios.isEmpty()) {
            response.put("message", "포트폴리오 조회 성공");
        } else {
            response.put("message", "포트폴리오가 존재하지 않습니다.");
        }
        response.put("data", portfolios);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/asset")
    public ResponseEntity<?> getKRWHoldings(@AuthenticationPrincipal PrincipalDetails principal) {
        String username = principal.getUsername();

        Long asset = userService.getUserAsset(username);

        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "원화 자산 조회 성공");
        response.put("asset", asset);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/infos")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal PrincipalDetails principal) {
        String username = principal.getUsername();
        UserInfoDto userInfo = userService.getUserInfo(username);

        return ResponseEntity.ok(userInfo);
    }

}