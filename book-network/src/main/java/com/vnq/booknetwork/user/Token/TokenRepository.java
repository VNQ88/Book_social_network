package com.vnq.booknetwork.user.Token;

import com.vnq.booknetwork.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);
    Optional<Token> findByUser(User user);
    void deleteByToken(String token);
    void deleteByUser(User user);
}
