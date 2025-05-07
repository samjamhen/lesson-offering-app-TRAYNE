package com.example.trayne.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trayne.entity.Instructor;

public interface InstructorRepo extends JpaRepository<Instructor, Long>{
    Optional<Instructor> findByEmail(String email);
}
