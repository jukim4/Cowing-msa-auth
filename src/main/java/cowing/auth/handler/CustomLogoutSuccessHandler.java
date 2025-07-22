package cowing.auth.handler;

import cowing.auth.jwt.JwtRedis;
import cowing.auth.jwt.TokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final JwtRedis jwtRedis;
    private final TokenProvider tokenProvider;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        String refreshToken = resolveRefreshToken(request);
        String username = tokenProvider.getUserIdFromRefreshToken(refreshToken);
        if (StringUtils.hasText(refreshToken)) {
            jwtRedis.deleteToken(username);
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().flush();
    }
    private String resolveRefreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("Authorization");
        if (StringUtils.hasText(refreshToken) && refreshToken.startsWith("Bearer ")) {
            return refreshToken.substring(7);
        }
        return null;
    }
}