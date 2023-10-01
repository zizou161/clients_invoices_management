package com.example.clientcrud.services;

import com.example.clientcrud.entities.Client;

import java.util.Optional;

public interface ClientService {

    Client saveClient(Client client);
    Iterable<Client> findAllClients();
    Optional<Client> findClientById(Long id);
    Client updaClient(Client client, Long clientId);

    void deleteClient(Long clientId);
    void createDummyClient();
}
