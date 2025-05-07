package com.example.trayne.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trayne.entity.Event;

public interface EventRepo extends JpaRepository<Event, Long>{
    
}
