package com.example.clientcrud.services;

import com.example.clientcrud.dto.request.InvoiceRequestDto;
import com.example.clientcrud.entities.Client;
import com.example.clientcrud.entities.Invoice;
import com.example.clientcrud.entities.Product;

import java.util.Optional;

public interface InvoiceService {

    Invoice saveInvoice(Invoice invoice);

    Iterable<Invoice> findAllInvoices();

    Optional<Invoice> findInvoiceById(String id);

    Invoice updateInvoice(Invoice invoice, String id);

    void deleteInvoice(String id);

    Iterable<Invoice> findInvoiceByClient(String clientId);

    Optional<Invoice> appendInvoiceToClient(InvoiceRequestDto invoice, Client client, Product product);

}
