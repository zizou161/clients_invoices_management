package com.example.clientcrud.services;

import com.example.clientcrud.dto.response.InvoiceItemResponseDto;
import com.example.clientcrud.entities.Invoice;
import com.example.clientcrud.entities.InvoiceItem;
import com.example.clientcrud.entities.InvoiceItemPK;
import com.example.clientcrud.entities.Product;

import java.util.Iterator;
import java.util.Optional;

public interface InvoiceItemService {

    Iterator<InvoiceItemResponseDto> findAllInvoiceItems(String invoiceId);

    Optional<InvoiceItem> findInvoiceItemById(String invoiceItemId);

    InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem, String invoiceItemId);

    InvoiceItem appendInvoiceItem(Invoice invoice, Product product, long quantity);
}
