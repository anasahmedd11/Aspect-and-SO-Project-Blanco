package com.example.DatabaseService.repository;

import com.example.DatabaseService.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> getUsersByEmail(String email);
    boolean existsByEmail(String email);
}