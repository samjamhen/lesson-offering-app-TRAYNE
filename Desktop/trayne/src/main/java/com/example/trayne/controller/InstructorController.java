package com.example.trayne.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trayne.entity.Instructor;
import com.example.trayne.service.InstructorService;

//for exposing endpoints
@RestController
@RequestMapping("/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = instructorService.getAll();
        return ResponseEntity.ok(instructors);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Instructor>> getInstructorById(@PathVariable("id") Long id){
        Optional<Instructor> instructor = instructorService.getById(id);
        return ResponseEntity.ok(instructor);
    }

    @PostMapping("/add")
    public ResponseEntity<Instructor> addBooking(@RequestBody Instructor instructor){
        instructorService.create(instructor);
        return ResponseEntity.ok(instructor);
    }

    @PutMapping
    public ResponseEntity<Instructor> updateBooking(@PathVariable("id") Long id, @RequestBody Instructor instructor){
        instructorService.update(id, instructor);
        return ResponseEntity.ok(instructor);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Instructor>> deleteBooking(@PathVariable("id") Long id){
        instructorService.deleteByInstructorId(id);
        return ResponseEntity.ok(instructorService.getById(id));
    }
}