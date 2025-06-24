package com.example.loginapp.repository;

import com.example.loginapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 이메일로 사용자 검색
    Optional<UserEntity> findByEmail(String email);

    // 닉네임 중복 확인용 (필요하면 사용)
    boolean existsByNickname(String nickname);

    // 이메일 중복 확인용 (필요하면 사용)
    boolean existsByEmail(String email);

}
