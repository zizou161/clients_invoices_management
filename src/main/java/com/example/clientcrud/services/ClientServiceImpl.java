package com.example.clientcrud.services;

import com.example.clientcrud.dto.request.ClientRequestDto;
import com.example.clientcrud.dto.response.ClientResponseDto;
import com.example.clientcrud.entities.*;
import com.example.clientcrud.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client saveClient(ClientRequestDto client) {
        Client clientEntity = new Client();
        String c = "select * from sche";
        clientEntity.setName(client.getName());
        clientEntity.setAddress(client.getAddress());
        return clientRepository.save(clientEntity);
    }

    @Override
    public Iterator<ClientResponseDto> findAllClients() {
        Iterable<Client> clients = clientRepository.findAll();
        Stream<ClientResponseDto> clientsStream = StreamSupport.stream(clients.spliterator(), false).map(ClientResponseDto::new);
        return clientsStream.iterator();
    }

    @Override
    public Optional<Client> findClientById(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public ClientResponseDto updateClient(ClientRequestDto client, String clientId) {
        Optional<Client> clientDB = findClientById(clientId);
        if (clientDB.isPresent()) {
            Client newClientVals = clientDB.get();
            newClientVals.setName(client.getName());
            newClientVals.setAddress(client.getAddress());
            return new ClientResponseDto(clientRepository.save(newClientVals));
        } else {
            return null;
        }

    }

    @Override
    public Iterator<ClientResponseDto> findClientsPagedSorted(Integer pageNo, Integer pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Client> clientsPaged = clientRepository.findAll(pageRequest);
        Stream<ClientResponseDto> clientsPagedStream = StreamSupport.stream(clientsPaged.spliterator(), false).map(ClientResponseDto::new);
        return clientsPagedStream.iterator();
    }

}
