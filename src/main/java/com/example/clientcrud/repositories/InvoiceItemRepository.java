package com.example.clientcrud.repositories;

import com.example.clientcrud.entities.InvoiceItem;
import com.example.clientcrud.entities.InvoiceItemPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemRepository extends CrudRepository<InvoiceItem, String> {
     Iterable<InvoiceItem> findInvoiceItemByInvoice_Id(String invoiceId);
}
