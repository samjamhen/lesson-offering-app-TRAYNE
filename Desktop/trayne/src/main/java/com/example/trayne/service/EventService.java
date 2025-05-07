package com.example.trayne.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trayne.entity.Event;
import com.example.trayne.exceptions.ResourceNotFoundException;
import com.example.trayne.repo.EventRepo;

@Service
public class EventService {
    private final EventRepo EventRepo;

    @Autowired
    public EventService(EventRepo EventRepo) {
        this.EventRepo = EventRepo;
    }

    public List<Event> getEvents() {
        return EventRepo.findAll();
    }

    public Event addEvent(Event Event) {
        return EventRepo.save(Event);
    }

    public Event updateEvent(Long id, Event Event) {
        if (!EventRepo.existsById(id)) {
            throw new ResourceNotFoundException("No Event found with id " + id);
        }
        return EventRepo.save(Event);
    }

    public Event findEventById(Long id) {
        return EventRepo.findById(id).orElse(null);
    }

    public void deleteEventById(Long id) {
        EventRepo.deleteById(id);
    }

}
