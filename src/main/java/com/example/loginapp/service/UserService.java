package com.example.loginapp.service;

import com.example.loginapp.entity.UserEntity;
import com.example.loginapp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 회원가입
    public boolean registerUser(String email, String rawPassword, String nickname) {
        if (userRepository.existsByEmail(email)) {
            return false; // 이미 존재하는 이메일이면 실패
        }
        String hashedPassword = passwordEncoder.encode(rawPassword);
        UserEntity user = UserEntity.builder()
                .email(email)
                .passwd(hashedPassword)
                .nickname(nickname)
                .build();
        userRepository.save(user);
        return true;
    }

}