package com.example.clientcrud.services;

import com.example.clientcrud.entities.Client;

public interface ClientService {

    Client saveClient(Client client);
    Iterable<Client> findAllClients();
    Client updaClient(Client client, Long clientId);

    void deleteClient(Long clientId);
    void createDummyClient();
}
