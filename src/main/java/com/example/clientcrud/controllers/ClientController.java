package com.example.clientcrud.controllers;

import com.example.clientcrud.dto.request.ClientRequestDto;
import com.example.clientcrud.dto.response.ClientResponseDto;
import com.example.clientcrud.entities.Client;
import com.example.clientcrud.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

@RestController
public class ClientController {
    @Autowired
    ClientServiceImpl clientServiceImpl;

    @GetMapping("/client")
    public ResponseEntity<Iterator<ClientResponseDto>> getClientsList() {
        try {
            Iterator<ClientResponseDto> clients = clientServiceImpl.findAllClients();
            if (clients.hasNext()) {
                return new ResponseEntity<>(clients, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable("id") String id) {
        try {
            Optional<Client> client = clientServiceImpl.findClientById(id);
            return client
                    .map(ClientResponseDto::new)
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/client")
    public ResponseEntity<Client> createClient(@RequestBody ClientRequestDto client) {
        try {
            Client clientSaved = clientServiceImpl.saveClient(client);
            return new ResponseEntity<>(clientSaved, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<ClientResponseDto> modifyClient(@PathVariable("id") String id,
                                                          @RequestBody ClientRequestDto client) {
        try {
            ClientResponseDto newClientVals = clientServiceImpl.updateClient(client, id);
            if (newClientVals == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(newClientVals, HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/client/paged")
    public ResponseEntity<Iterator<ClientResponseDto>> findClientsPagedSorted(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                                                              @RequestParam(defaultValue = "name") String sortBy) {
        try {
            Iterator<ClientResponseDto> clients = clientServiceImpl.findClientsPagedSorted(pageNo, pageSize, sortBy);
            if (clients.hasNext()) {
                return new ResponseEntity<>(clients, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
