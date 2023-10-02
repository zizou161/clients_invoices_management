package com.example.clientcrud.controllers;

import com.example.clientcrud.entities.Invoice;
import com.example.clientcrud.entities.Product;
import com.example.clientcrud.services.InvoiceServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceServiceImpl invoiceService;

    @GetMapping("/invoice")
    public ResponseEntity<Iterable<Invoice>> getInvoicesList() {
        try {
         Iterable<Invoice> invoices = invoiceService.findAllInvoices();
         if (invoices.iterator().hasNext()) {
             return new ResponseEntity<>(invoices,HttpStatus.OK);
         }
         else {
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invoice/{id}")
    public ResponseEntity<Optional<Invoice>> searchInvoiceById(@PathVariable("id") Long id) {
        try {
            Optional<Invoice> invoice = invoiceService.findInvoiceById(id);
            if(invoice.isPresent()) {
                return new ResponseEntity<>(invoice,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/invoice")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        try{
            Invoice invoiceCreated = invoiceService.saveInvoice(invoice);
            return new ResponseEntity<>(invoiceCreated,HttpStatus.CREATED);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/invoice/{id}")
    public ResponseEntity<Invoice> modifyInvoice(@PathVariable("id") Long id,
                                                 @RequestBody Invoice invoice) {
        try {
            Invoice newInvoiceVals = invoiceService.updateInvoice(invoice, id);
            if (newInvoiceVals == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(newInvoiceVals, HttpStatus.OK);
            }
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/invoice/{id}")
    public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("id") Long id) {
        try {
            invoiceService.deleteInvoice(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
