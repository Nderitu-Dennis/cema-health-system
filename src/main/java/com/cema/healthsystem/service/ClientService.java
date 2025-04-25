package com.cema.healthsystem.service;

import com.cema.healthsystem.entity.Client;
import com.cema.healthsystem.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService ( ClientRepository clientRepository){
        this.clientRepository=clientRepository;
    }

    // register a new client
    public Client registerClient(Client client){
        return clientRepository.save(client);
    }

    // find client by email
    public Optional<Client> findClientByEmail(String email){
        return Optional.ofNullable(clientRepository.findByEmail(email));
    }

    // find client by phone number
    public Optional<Client> findClientByPhoneNumber(String phoneNumber){
        return Optional.ofNullable(clientRepository.findByPhoneNumber(phoneNumber));
    }

    // view client profile by id
    public Optional<Client> getClientProfile(Long clientId){
        return clientRepository.findById(clientId);
    }

    // get all clients

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }
}
