package com.example.clientcrud.services;

import com.example.clientcrud.dto.request.InvoiceRequestDto;
import com.example.clientcrud.dto.response.InvoiceResponseDto;
import com.example.clientcrud.entities.*;
import com.example.clientcrud.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
    public Iterator<InvoiceResponseDto> findAllInvoices() {
        Iterable<Invoice> invoices = invoiceRepository.findAll();
        Stream<InvoiceResponseDto> invoicesStream = StreamSupport.stream(invoices.spliterator(), false).map(InvoiceResponseDto::new);
        return invoicesStream.iterator();
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
    public Iterator<InvoiceResponseDto> findInvoiceByClient(String clientId) {
        Iterable<Invoice> invoices = invoiceRepository.findInvoiceByClient_Id(clientId);
        Stream<InvoiceResponseDto> invoicesStream = StreamSupport.stream(invoices.spliterator(), false).map(InvoiceResponseDto::new);
        return invoicesStream.iterator();
    }

    @Override
    public Optional<InvoiceResponseDto> appendInvoiceToClient(InvoiceRequestDto invoice, Client client, Product product) {
        Invoice invoiceEntity = new Invoice();
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceEntity.setDate(new Date());
        invoiceEntity.setClient(client);
        invoiceItem.setProduct(product);
        invoiceItem.setInvoice(invoiceEntity);
        invoiceItem.setQuantity(invoice.getQuantity());
        invoiceEntity.addInvoiceItem(invoiceItem);
        client.addInvoice(invoiceEntity);
        return Optional.of(new InvoiceResponseDto(invoiceRepository.save(invoiceEntity)));
    }

}
