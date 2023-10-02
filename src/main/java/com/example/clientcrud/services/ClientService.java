package com.example.clientcrud.services;

import com.example.clientcrud.entities.Client;
import com.example.clientcrud.entities.Invoice;

import java.util.Optional;

public interface ClientService {

    Client saveClient(Client client);
    Iterable<Client> findAllClients();
    Optional<Client> findClientById(Long id);
    Client updateClient(Client client, Long clientId);

    void deleteClient(Long clientId);
    Invoice appendInvoice(Invoice invoice,Long clientId);
    void createDummyClient();
}
