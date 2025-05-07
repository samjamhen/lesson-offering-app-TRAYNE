package com.example.trayne.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trayne.entity.Client;
import com.example.trayne.exceptions.ResourceNotFoundException;
import com.example.trayne.repo.ClientRepo;

@Service
public class ClientService {

    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public List<Client> getAllClients() {
        return clientRepo.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepo.findById(id);
    }

    public Client addClient(Client client) {
        return clientRepo.save(client);
    }

    public Client updateClient(Long id, Client updatedClient) {
        if (!clientRepo.existsById(id)) {
            throw new ResourceNotFoundException("Client not found");
        }
        updatedClient.setId(id);
        return clientRepo.save(updatedClient);
    }

    public void deleteClientById(Long id) {
        clientRepo.deleteById(id);
    }

    public Optional<Client> getClientByEmail(String email){
        return clientRepo.findByEmail(email);
    }

    public Client updateClientByEmail(String email, Client updatedData) {
        Client existingClient = clientRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Client not found with email: " + email));
    
        existingClient.setFullName(updatedData.getFullName());
        existingClient.setPhoneNumber(updatedData.getPhoneNumber());
        existingClient.setPassword(updatedData.getPassword());
        existingClient.setEvents(updatedData.getEvents());
    
        return clientRepo.save(existingClient);
    }
    
}
