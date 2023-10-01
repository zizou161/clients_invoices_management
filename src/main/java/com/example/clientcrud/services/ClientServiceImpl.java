package com.example.clientcrud.services;

import com.example.clientcrud.entities.*;
import com.example.clientcrud.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Iterable<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client updaClient(Client client, Long clientId) {
        Optional<Client> clientDB = findClientById(clientId);
        if (clientDB.isPresent()) {
            Client newClientVals = clientDB.get();
            newClientVals.setName(client.getName());
            newClientVals.setAddress(client.getAddress());
            newClientVals.setInvoices(client.getInvoices());
            return clientRepository.save(newClientVals);
        } else {
            return null;
        }

    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public void createDummyClient() {
        Client client = new Client();
        Invoice invoice = new Invoice();
        Product product = new Product();
        ProductInvoice productInvoice = new ProductInvoice();
        client.setName("khorsi mustapha");
        client.setAddress("cite selem1 n80");
        invoice.setDate(new Date());
        product.setName("shampoo");
        product.setPrice(new BigDecimal(20));
        productInvoice.setInvoice(invoice);
        productInvoice.setProduct(product);
        productInvoice.setQuantity(3);
        client.addInvoice(invoice);
        clientRepository.save(client);
    }
}
