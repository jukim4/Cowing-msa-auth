package cowing.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "닉네임 변경 DTO")
public record NicknameChangeDto(
    @Schema(description = "유저 닉네임", example = "cowingNickname")
    String nickname) {
}
