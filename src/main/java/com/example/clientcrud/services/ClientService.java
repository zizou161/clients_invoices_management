package com.example.clientcrud.services;

import com.example.clientcrud.entities.Client;
import com.example.clientcrud.entities.Invoice;

import java.util.Optional;

public interface ClientService {

    Client saveClient(Client client);

    Iterable<Client> findAllClients();

    Optional<Client> findClientById(String id);

    Client updateClient(Client client, String clientId);

    void deleteClient(String clientId);
    Invoice appendInvoice(Invoice invoice, String clientId);

    void createDummyClient();
}
