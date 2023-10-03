package com.example.clientcrud.services;

import com.example.clientcrud.entities.InvoiceItem;
import com.example.clientcrud.entities.InvoiceItemPK;
import com.example.clientcrud.repositories.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {
    @Autowired
    InvoiceItemRepository invoiceItemRepository;

    @Override
    public Iterable<InvoiceItem> findAllInvoiceItems(Long invoiceId) {
        return invoiceItemRepository.findInvoiceItemByInvoice_Id(invoiceId);
    }

    @Override
    public Optional<InvoiceItem> findInvoiceItemById(InvoiceItemPK invoiceItemPK) {
        return invoiceItemRepository.findById(invoiceItemPK);
    }

    @Override
    public InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem, InvoiceItemPK invoiceItemPK) {
        Optional<InvoiceItem> invoiceItemDB = findInvoiceItemById(invoiceItemPK);
        if (invoiceItemDB.isPresent()) {
            InvoiceItem newInvoiceItemVals = invoiceItemDB.get();
            newInvoiceItemVals.setQuantity(invoiceItem.getQuantity());
            return invoiceItemRepository.save(newInvoiceItemVals);
        } else {
            return null;
        }
    }
}
