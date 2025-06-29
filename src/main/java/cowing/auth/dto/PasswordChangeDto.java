package cowing.auth.dto;

public record PasswordChangeDto(
        String email,
        String currentPwd,
        String newPwd) {
}