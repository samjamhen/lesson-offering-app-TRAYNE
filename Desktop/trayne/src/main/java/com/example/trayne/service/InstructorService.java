package com.example.trayne.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trayne.entity.Instructor;
import com.example.trayne.exceptions.ResourceNotFoundException;
import com.example.trayne.repo.InstructorRepo;

@Service
public class InstructorService {
    private final InstructorRepo instructorRepo;

    @Autowired
    public InstructorService(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
    }

    public List<Instructor> getAll() {
        return instructorRepo.findAll();
    }

    public Optional<Instructor> getById(Long id) {
        return instructorRepo.findById(id);
    }

    public Instructor create(Instructor instructor) {
        return instructorRepo.save(instructor);
    }

    public Instructor update(Long id, Instructor instructor) {
        if (!instructorRepo.existsById(id)) {
            throw new ResourceNotFoundException("No Instructor found with id " + id);
        }
        return instructorRepo.save(instructor);
    }

    public void deleteByInstructorId(Long id) {
        if (!instructorRepo.existsById(id)) {
            throw new ResourceNotFoundException("No Instructor found with id " + id);
        }
        instructorRepo.deleteById(id);
    }
}
