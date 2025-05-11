package com.example.AuthenticationService.repository;

import com.example.AuthenticationService.Entity.User;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
