package cowing.auth.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "회원 정보 조회 DTO")
public record UserInfoDto(
        @Schema(description = "유저 닉네임", example = "testnickname")
        String nickname,
        @Schema(description = "이메일", example = "test@test.com")
        String email,
        @Schema(description = "유저 ID", example = "test")
        String username) {
}