package com.example.simpleboardauth.user.repository;

import com.example.simpleboardauth.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User
        , Long> {
    Optional<User> findByUsername(String username);
}
