package com.example.trayne.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trayne.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
