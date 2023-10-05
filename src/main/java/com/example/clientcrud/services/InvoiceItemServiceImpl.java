package com.example.clientcrud.services;

import com.example.clientcrud.entities.*;
import com.example.clientcrud.repositories.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {
    @Autowired
    InvoiceItemRepository invoiceItemRepository;
    @Autowired
    ProductServiceImpl productService;

    @Override
    public Iterable<InvoiceItem> findAllInvoiceItems(String invoiceId) {
        return invoiceItemRepository.findInvoiceItemByInvoice_Id(invoiceId);
    }

    @Override
    public Optional<InvoiceItem> findInvoiceItemById(String invoiceItemId) {
        return invoiceItemRepository.findById(invoiceItemId);
    }

    @Override
    public InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem, String invoiceItemId) {
        Optional<InvoiceItem> invoiceItemDB = findInvoiceItemById(invoiceItemId);
        if (invoiceItemDB.isPresent()) {
            InvoiceItem newInvoiceItemVals = invoiceItemDB.get();
            newInvoiceItemVals.setQuantity(invoiceItem.getQuantity());
            return invoiceItemRepository.save(newInvoiceItemVals);
        } else {
            return null;
        }
    }

    @Override
    public InvoiceItem appendInvoiceItem(Invoice invoice, Product product, long quantity) {
        InvoiceItem invoiceItem = new InvoiceItem(invoice, product, quantity);
        invoice.addInvoiceItem(invoiceItem);
        product.addInvoiceItem(invoiceItem);
        return invoiceItemRepository.save(invoiceItem);
    }

    @Override
    public Optional<InvoiceItem> saveInvoiceItem(InvoiceItem item, Invoice invoice) {
/*        String productId = item.getInvoiceItemPK().getProductId();
        Optional<Product> product = productService.findProductById(productId);
        if (product.isPresent()) {
            item.setProduct(product.get());
            item.setInvoice(invoice);
            item.getInvoiceItemPK().setInvoiceId(invoice.getUuid());
            return Optional.of(invoiceItemRepository.save(item));
        } else {
            return Optional.empty();
        }*/
    return Optional.empty();
    }
}
