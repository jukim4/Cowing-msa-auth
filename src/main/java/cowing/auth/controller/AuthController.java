package cowing.auth.controller;

import cowing.auth.dto.LoginReqDto;
import cowing.auth.dto.TokenDto;
import cowing.auth.exception.RefreshTokenInvalidException;
import cowing.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginReqDto authReqDto){
        TokenDto token = authService.login(authReqDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(HttpServletRequest request) throws RefreshTokenInvalidException {
        TokenDto token = authService.refresh(request);
        return ResponseEntity.ok(token);
    }
}