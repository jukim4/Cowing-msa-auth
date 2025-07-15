package cowing.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Builder
@Schema(description = "로그인 요청 DTO")
public record LoginReqDto(
        @Schema(description = "유저 ID", example = "cowinguser")
        @NotNull String username,

        @Schema(description = "유저 비밀번호", example = "cowing123")
        @NotNull String passwd
) {
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, passwd);
    }
}
