package cowing.auth.controller;

import cowing.auth.dto.LoginReqDto;
import cowing.auth.dto.TokenDto;
import cowing.auth.exception.RefreshTokenInvalidException;
import cowing.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="User Auth Service", description = "토큰 관련 API")

public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인 시 토큰 발급", description="사용자 로그인시 정보 인증 후 AccessToken과 RefreshToken 발급")
    @ApiResponse(responseCode = "200", description = "주문이 접수되었습니다.")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginReqDto authReqDto){
        TokenDto token = authService.login(authReqDto);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "토큰 재발급", description="Refresh Token을 통해 AccessToken을 재발급")
    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(HttpServletRequest request) throws RefreshTokenInvalidException {
        TokenDto token = authService.refresh(request);
        return ResponseEntity.ok(token);
    }
}