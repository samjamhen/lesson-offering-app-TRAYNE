package com.example.trayne.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trayne.entity.Client;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    
}