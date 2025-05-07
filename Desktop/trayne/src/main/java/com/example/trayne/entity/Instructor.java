package com.example.trayne.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("INSTRUCTOR")
public class Instructor extends User {

    private String specialization;
    
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    public Instructor(){}

    public Instructor(String fullName, String phoneNumber, String email, String password, String specialization, List<String> availableCities, List<Event> events)
    {
        super(fullName, phoneNumber, email, password);
        this.specialization = specialization;
        this.events = events;
    }

    public Instructor(long id, String fullName, String phoneNumber, String email, String password, String specialization, List<String> availableCities, List<Event> events)
    {
        super(id, fullName, phoneNumber, email, password);
        this.specialization = specialization;
        this.events = events;
    }

    public void addEvent(Event event)
    {
        events.add(event);
    }
}
