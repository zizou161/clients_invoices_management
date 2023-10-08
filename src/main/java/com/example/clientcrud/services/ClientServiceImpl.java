package com.example.clientcrud.services;

import com.example.clientcrud.dto.request.ClientRequestDto;
import com.example.clientcrud.dto.response.ClientResponseDto;
import com.example.clientcrud.entities.*;
import com.example.clientcrud.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void deleteClient(String clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public Invoice appendInvoice(Invoice invoice, String clientId) {
        Optional<Client> client = findClientById(clientId);
        if (client.isPresent()) {
            client.get().addInvoice(invoice);
            clientRepository.save(client.get());
            return invoice;
        } else {
            return null;
        }
    }

    @Override
    public void createDummyClient() {
        Client client = new Client();
        Invoice invoice = new Invoice();
        Product product = new Product();
        InvoiceItem invoiceItem = new InvoiceItem();
        client.setName("khorsi mustapha");
        client.setAddress("cite selem1 n80");
        invoice.setDate(new Date());
        product.setName("shampoo");
        product.setPrice(new BigDecimal(20));
        invoiceItem.setInvoice(invoice);
        invoiceItem.setProduct(product);
        invoiceItem.setQuantity(3);
        client.addInvoice(invoice);
        clientRepository.save(client);
    }
}
