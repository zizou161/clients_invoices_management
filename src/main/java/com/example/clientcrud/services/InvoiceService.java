package com.example.clientcrud.services;

import com.example.clientcrud.entities.Invoice;

import java.util.Optional;

public interface InvoiceService {

    Invoice saveInvoice(Invoice invoice);

    Iterable<Invoice> findAllInvoices();

    Optional<Invoice> findInvoiceById(Long id);

    Invoice updateInvoice(Invoice invoice, Long id);

    void deleteInvoice(Long id);

    Iterable<Invoice> findInvoiceByClient(Long clientId);
}
