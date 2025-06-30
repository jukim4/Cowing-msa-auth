package cowing.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Builder
public record LoginReqDto(
        @NotNull String username,
        @NotNull String passwd
) {
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, passwd);
    }
}
