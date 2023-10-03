package com.example.clientcrud.repositories;

import com.example.clientcrud.entities.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String> {
    public Iterable<Invoice> findInvoiceByClient_Uuid(String client_uuid);
}
