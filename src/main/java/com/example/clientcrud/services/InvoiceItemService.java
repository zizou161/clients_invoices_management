package com.example.clientcrud.services;

import com.example.clientcrud.entities.Invoice;
import com.example.clientcrud.entities.InvoiceItem;
import com.example.clientcrud.entities.InvoiceItemPK;
import com.example.clientcrud.entities.Product;

import java.util.Optional;

public interface InvoiceItemService {

    Iterable<InvoiceItem> findAllInvoiceItems(String invoiceId);
    Optional<InvoiceItem> findInvoiceItemById(InvoiceItemPK invoiceItemPK);

    InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem, InvoiceItemPK invoiceItemPK);

    InvoiceItem appendInvoiceItem(Invoice invoice, Product product, long quantity);
}
