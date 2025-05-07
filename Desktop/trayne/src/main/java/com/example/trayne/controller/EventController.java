package com.example.trayne.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trayne.entity.Event;
import com.example.trayne.service.EventService;

//for exposing endpoints
@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService EventService;

    public EventController(EventService EventService){
        this.EventService = EventService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> Events = EventService.getEvents();
        return ResponseEntity.ok(Events);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id){
        Event Event = EventService.findEventById(id);
        return ResponseEntity.ok(Event);
    }

    @PostMapping("/add")
    public ResponseEntity<Event> addEvent(@RequestBody Event Event){
        EventService.addEvent(Event);
        return ResponseEntity.ok(Event);
    }

    @PutMapping
    public ResponseEntity<Event> updateEvent(@PathVariable("id") Long id, @RequestBody Event Event){
        EventService.updateEvent(id, Event);
        return ResponseEntity.ok(Event);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") Long id){
        EventService.deleteEventById(id);
        return ResponseEntity.ok(EventService.findEventById(id));
    }
}