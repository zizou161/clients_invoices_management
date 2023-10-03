package com.example.clientcrud.services;

import com.example.clientcrud.entities.Invoice;
import com.example.clientcrud.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    InvoiceRepository invoiceRepository;
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
        return invoiceRepository.findInvoiceByClient_Uuid(clientId);
    }
}
