package cowing.auth.dto;

public record UserInfoDto(
        String nickname,
        String email,
        String username) {
}