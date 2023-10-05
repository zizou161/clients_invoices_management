package com.example.clientcrud.services;

import com.example.clientcrud.dto.request.InvoiceRequestDto;
import com.example.clientcrud.entities.*;
import com.example.clientcrud.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceItemServiceImpl invoiceItemService;
    @Autowired
    ProductServiceImpl productService;

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Iterable<Invoice> findAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> findInvoiceById(String id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice, String id) {
        Optional<Invoice> invoiceDB = findInvoiceById(id);
        if (invoiceDB.isPresent()) {
            Invoice newInvoiceVals = invoiceDB.get();
            newInvoiceVals.setDate(invoice.getDate());
            newInvoiceVals.setInvoiceItems(invoice.getInvoiceItems());
            return invoiceRepository.save(newInvoiceVals);
        } else {
            return null;
        }
    }

    @Override
    public void deleteInvoice(String id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public Iterable<Invoice> findInvoiceByClient(String clientId) {
        return invoiceRepository.findInvoiceByClient_Id(clientId);
    }

    @Override
    public Optional<Invoice> appendInvoiceToClient(InvoiceRequestDto invoice, Client client, Product product) {
        Invoice invoiceEntity = new Invoice();
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceEntity.setDate(new Date());
        invoiceEntity.setClient(client);
        invoiceItem.setProduct(product);
        invoiceItem.setInvoice(invoiceEntity);
        invoiceItem.setQuantity(invoice.getQuantity());
        invoiceEntity.addInvoiceItem(invoiceItem);
        client.addInvoice(invoiceEntity);
        return Optional.of(invoiceRepository.save(invoiceEntity));
    }

}
