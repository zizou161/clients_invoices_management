package com.example.clientcrud.services;

import com.example.clientcrud.dto.request.InvoiceRequestDto;
import com.example.clientcrud.dto.response.InvoiceResponseDto;
import com.example.clientcrud.entities.Client;
import com.example.clientcrud.entities.Invoice;
import com.example.clientcrud.entities.Product;

import java.util.Iterator;
import java.util.Optional;

public interface InvoiceService {

    Invoice saveInvoice(Invoice invoice);

    Iterator<InvoiceResponseDto> findAllInvoices();

    Optional<Invoice> findInvoiceById(String id);

    Invoice updateInvoice(Invoice invoice, String id);

    void deleteInvoice(String id);

    Iterator<InvoiceResponseDto> findInvoiceByClient(String clientId);

    Optional<InvoiceResponseDto> appendInvoiceToClient(InvoiceRequestDto invoice, Client client, Product product);

}
