package cowing.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "비밀번호 변경 DTO")
public record PasswordChangeDto(
        @Schema(description = "유저 Email", example = "cowinguser")
        String email,
        @Schema(description = "현재 비밀번호", example = "current123")
        String currentPwd,
        @Schema(description = "변경할 비밀번호", example = "new123")
        String newPwd) {
}