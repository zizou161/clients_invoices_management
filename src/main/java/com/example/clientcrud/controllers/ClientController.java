package com.example.clientcrud.controllers;

import com.example.clientcrud.entities.Client;
import com.example.clientcrud.entities.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.clientcrud.services.ClientServiceImpl;

import java.util.Optional;

@RestController
public class ClientController {
    @Autowired
    ClientServiceImpl clientServiceImpl;

    @GetMapping("/client")
    public ResponseEntity<Iterable<Client>> getClientsList() {
        try {
            Iterable<Client> clients = clientServiceImpl.findAllClients();
            if (clients.iterator().hasNext()) {
                return new ResponseEntity<>(clients, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        try {
            Optional<Client> client = clientServiceImpl.findClientById(id);
            return client
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/client")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        try {
            Client clientSaved = clientServiceImpl.saveClient(client);
            return new ResponseEntity<>(clientSaved, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> modifyClient(@PathVariable("id") Long id,
                                               @RequestBody Client client) {
        try {
            Client newClientVals = clientServiceImpl.updateClient(client, id);
            if (newClientVals == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(newClientVals, HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") Long id) {
        try {
            clientServiceImpl.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/client/{clientId}/invoices")
    public ResponseEntity<Invoice> createInvoiceForClient(@RequestBody Invoice invoice,
                                                          @PathVariable("clientId") Long clientId) {
        try {
            Invoice addedInvoice = clientServiceImpl.appendInvoice(invoice, clientId);
            if (addedInvoice == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(addedInvoice, HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/create_dummy_client")
    public void createDummyClient() {
        clientServiceImpl.createDummyClient();
    }


}
