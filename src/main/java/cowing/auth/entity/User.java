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
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String passwd;

    @Column(nullable = false)
    private String nickname;

    @Column(precision = 20, scale = 8, nullable = true)
    private BigDecimal uHoldings;

    public void updatePassword(String newPasswd) {
        this.passwd = newPasswd;
    }
}