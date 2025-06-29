package cowing.auth.service;

import cowing.auth.entity.User;
import cowing.auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import cowing.auth.dto.LoginResponseDto;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 회원가입
    public boolean registerUser(String email, String rawPassword, String nickname, String username) {
        try {
            if (userRepository.existsByEmail(email)) {
                throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
            }
            String hashedPassword = passwordEncoder.encode(rawPassword);
            User user = User.builder()
                    .email(email)
                    .passwd(hashedPassword)
                    .nickname(nickname)
                    .username(username)
                    .uHoldings(BigDecimal.ZERO)
                    .build();

            userRepository.save(user);
            return true;
        } catch (Exception e) {
            System.out.println("회원가입 실패: " + e.getMessage());
            return false;
        }
    }

    // 비밀번호 변경
    public boolean updatePassword(String email, String currentPwd, String newPwd) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty())
            return false;

        User user = optionalUser.get();

        if (!passwordEncoder.matches(currentPwd, user.getPasswd())) {
            return false;
        }

        String hashedNewPwd = passwordEncoder.encode(newPwd);
        user.setPasswd(hashedNewPwd);
        userRepository.save(user);
        return true;
    }
}