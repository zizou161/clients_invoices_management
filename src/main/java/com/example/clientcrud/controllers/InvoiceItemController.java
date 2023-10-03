package com.example.clientcrud.controllers;

import com.example.clientcrud.entities.InvoiceItem;
import com.example.clientcrud.entities.InvoiceItemPK;
import com.example.clientcrud.services.InvoiceItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class InvoiceItemController {

    @Autowired
    InvoiceItemServiceImpl invoiceItemService;

    @GetMapping("/invoice/{invoiceId}/item")
    public ResponseEntity<Iterable<InvoiceItem>> listItemsOfInvoice(@PathVariable("invoiceId") Long invoiceId) {
        try {
            Iterable<InvoiceItem> invoiceItems = invoiceItemService.findAllInvoiceItems(invoiceId);
            if (invoiceItems.iterator().hasNext()) {
                return new ResponseEntity<>(invoiceItems, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invoice_item")
    public ResponseEntity<InvoiceItem> getInvoiceItemById(@RequestBody InvoiceItemPK invoiceItemPK) {
        try {
            Optional<InvoiceItem> invoiceItem = invoiceItemService.findInvoiceItemById(invoiceItemPK);
            return invoiceItem
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
