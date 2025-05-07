package com.example.trayne.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {

    @ManyToMany
    @JoinTable(
        name = "client_events",
        joinColumns = @JoinColumn(name = "client_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events = new ArrayList<>();

    public Client(){}

    public Client(String fullName, String phoneNumber, String email, String password) {
        super(fullName, phoneNumber, email, password);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events){
        this.events = events;
    }

    public void addToEvents(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Events cannot be null");
        }
        events.add(event);
    }
}
