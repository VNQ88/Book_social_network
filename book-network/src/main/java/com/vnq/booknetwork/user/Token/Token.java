package com.vnq.booknetwork.user.Token;

import com.vnq.booknetwork.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Token {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "validatedAt")
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
