package cowing.auth.repository;

import aj.org.objectweb.asm.commons.Remapper;
import cowing.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 사용자 검색
    Optional<User> findByEmail(String email);

    // 닉네임 중복 확인용 (필요하면 사용)
    boolean existsByNickname(String nickname);

    // 이메일 중복 확인용 (필요하면 사용)
    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
}
