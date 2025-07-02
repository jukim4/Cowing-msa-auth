package cowing.auth.service;

import cowing.auth.dto.PortfolioDto;
import cowing.auth.entity.Portfolio;
import cowing.auth.entity.User;
import cowing.auth.repository.PortfolioRepository;
import cowing.auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PortfolioRepository portfolioRepository;

    public UserService(UserRepository userRepository, PortfolioRepository portfolioRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.portfolioRepository = portfolioRepository;
    }

    @Transactional
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
                    .uHoldings(10000000L)
                    .build();

            userRepository.save(user);
            return true;
        } catch (Exception e) {
            System.out.println("회원가입 실패: " + e.getMessage());
            return false;
        }
    }

    @Transactional
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

    @Transactional(readOnly = true)
    public List<PortfolioDto> getPortfolio(String username) {
        return portfolioRepository.findByUsername(username).stream()
                .map(p -> new PortfolioDto(
                        p.getUsername(),
                        p.getQuantity(),
                        p.getAverageCost()
                ))
                .toList();

    }

    @Transactional(readOnly = true)
    public Long getUserAsset(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return user.getUHoldings();
    }

}