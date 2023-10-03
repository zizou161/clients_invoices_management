package com.example.clientcrud.services;

import com.example.clientcrud.entities.Invoice;
import com.example.clientcrud.entities.InvoiceItem;
import com.example.clientcrud.entities.InvoiceItemPK;

import java.util.Optional;

public interface InvoiceItemService {

    Iterable<InvoiceItem> findAllInvoiceItems(Long invoiceId);
    Optional<InvoiceItem> findInvoiceItemById(InvoiceItemPK invoiceItemPK);

    InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem, InvoiceItemPK invoiceItemPK);
}
