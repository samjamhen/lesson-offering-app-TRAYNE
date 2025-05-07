package com.example.trayne.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trayne.entity.Client;
import com.example.trayne.entity.Event;
import com.example.trayne.service.ClientService;
import com.example.trayne.service.EventService;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final EventService eventService;

    @Autowired
    public ClientController (ClientService clientService, EventService eventService) {
        this.clientService = clientService;
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("email/{email}")
    public ResponseEntity<Client> getClientByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.addClient(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.updateClient(id, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/attend/{userId}/{eventId}")
    public ResponseEntity<?> attendEvent(@PathVariable Long userId, @PathVariable Long eventId) {
        Client client = clientService.getClientById(userId)
            .orElseThrow(() -> new RuntimeException("Client not found"));

        Event event = eventService.findEventById(eventId);
        if (event == null) {
            return ResponseEntity.badRequest().body("Event not found");
        }

        if (client.getEvents().contains(event)) {
            return ResponseEntity.badRequest().body("Client is already registered for this event.");
        }

        client.addToEvents(event);
        clientService.updateClient(userId, client);

        return ResponseEntity.ok("Client registered for event");
    }
}
