package cowing.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwd;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Authority authority;

    @Column(precision = 20, scale = 8, nullable = true)
    private Long uHoldings;

    @PrePersist
    public void prePersist() {
            this.authority = Authority.ROLE_USER; // 기본 권한 설정
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}