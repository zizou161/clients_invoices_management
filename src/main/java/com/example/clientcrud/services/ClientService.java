package com.example.clientcrud.services;

import com.example.clientcrud.dto.request.ClientRequestDto;
import com.example.clientcrud.dto.response.ClientResponseDto;
import com.example.clientcrud.entities.Client;
import com.example.clientcrud.entities.Invoice;

import java.util.Iterator;
import java.util.Optional;

public interface ClientService {

    Client saveClient(ClientRequestDto client);

    Iterator<ClientResponseDto> findAllClients();

    Optional<Client> findClientById(String id);

    ClientResponseDto updateClient(ClientRequestDto client, String clientId);

    void deleteClient(String clientId);

    Invoice appendInvoice(Invoice invoice, String clientId);

    void createDummyClient();
}
