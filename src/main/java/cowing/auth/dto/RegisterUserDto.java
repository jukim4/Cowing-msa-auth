package cowing.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "회원가입 DTO")
public record RegisterUserDto(
        @Schema(description = "이메일", example = "test@test.com")
        @JsonProperty("email") @NotBlank @Email String email,
        @Schema(description = "비밀번호", example = "test")
        @JsonProperty("passwd") @NotBlank String passwd,
        @Schema(description = "유저 ID", example = "test")
        @JsonProperty("nickname") @NotBlank String nickname,
        @Schema(description = "유저 닉네임", example = "testnickname")
        @JsonProperty("username") @NotBlank String username) {
}