package cowing.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "액세스 토큰 리프레쉬 DTO")
public record TokenDto(
        @Schema(description = "인증 타입", example = "Bearer")
        String grantType,
        @Schema(description = "갱신된 토큰 정보", example = "derwfqwef")
        String accessToken,
        @Schema(description = "갱신된 토큰 정보", example = "SDfefsdfae")
        String refreshToken) {}
