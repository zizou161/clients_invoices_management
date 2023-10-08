package com.example.clientcrud.services;

import com.example.clientcrud.dto.request.InvoiceItemRequestDto;
import com.example.clientcrud.dto.request.InvoiceItemUpdateRequestDto;
import com.example.clientcrud.dto.response.InvoiceItemResponseDto;
import com.example.clientcrud.dto.response.InvoiceResponseDto;
import com.example.clientcrud.entities.*;
import com.example.clientcrud.repositories.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {
    @Autowired
    InvoiceItemRepository invoiceItemRepository;
    @Autowired
    ProductServiceImpl productService;

    @Override
    public Iterator<InvoiceItemResponseDto> findAllInvoiceItems(String invoiceId) {
        Stream<InvoiceItemResponseDto> itemsResponseDtoStream = StreamSupport.stream(invoiceItemRepository.findInvoiceItemByInvoice_Id(invoiceId).spliterator(), false).map(InvoiceItemResponseDto::new);
        return itemsResponseDtoStream.iterator();
    }

    @Override
    public Optional<InvoiceItem> findInvoiceItemById(String invoiceItemId) {
        return invoiceItemRepository.findById(invoiceItemId);
    }

    @Override
    public InvoiceItemResponseDto updateInvoiceItem(String invoiceItemId, InvoiceItemUpdateRequestDto invoiceItem) {
        Optional<InvoiceItem> invoiceItemDB = findInvoiceItemById(invoiceItemId);
        if (invoiceItemDB.isPresent()) {
            InvoiceItem newInvoiceItemVals = invoiceItemDB.get();
            newInvoiceItemVals.setQuantity(invoiceItem.getQuantity());
            return new InvoiceItemResponseDto(invoiceItemRepository.save(newInvoiceItemVals));
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
    public void deleteInvoiceItem(String id) {
        invoiceItemRepository.deleteById(id);
    }
}
