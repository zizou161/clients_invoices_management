package com.example.clientcrud.controllers;

import com.example.clientcrud.dto.request.InvoiceRequestDto;
import com.example.clientcrud.entities.Client;
import com.example.clientcrud.entities.Invoice;
import com.example.clientcrud.entities.Product;
import com.example.clientcrud.services.ClientServiceImpl;
import com.example.clientcrud.services.InvoiceServiceImpl;
import com.example.clientcrud.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceServiceImpl invoiceService;
    @Autowired
    ClientServiceImpl clientServiceImpl;
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/invoice")
    public ResponseEntity<Iterable<Invoice>> getInvoicesList() {
        try {
            Iterable<Invoice> invoices = invoiceService.findAllInvoices();
            if (invoices.iterator().hasNext()) {
                return new ResponseEntity<>(invoices, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invoice/{id}")
    public ResponseEntity<Invoice> searchInvoiceById(@PathVariable("id") String id) {
        try {
            Optional<Invoice> invoice = invoiceService.findInvoiceById(id);
            return invoice
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/invoice/{id}")
    public ResponseEntity<Invoice> modifyInvoice(@PathVariable("id") String id,
                                                 @RequestBody Invoice invoice) {
        try {
            Invoice newInvoiceVals = invoiceService.updateInvoice(invoice, id);
            if (newInvoiceVals == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(newInvoiceVals, HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/invoice/{id}")
    public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("id") String id) {
        try {
            invoiceService.deleteInvoice(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/client/{clientId}/invoices")
    public ResponseEntity<Iterable<Invoice>> getInvoiceByClientId(@PathVariable("clientId") String clientId) {
        try {
            Iterable<Invoice> invoices = invoiceService.findInvoiceByClient(clientId);
            if (invoices.iterator().hasNext()) {
                return new ResponseEntity<>(invoices, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/client/{clientId}/invoices")
    public ResponseEntity<Invoice> createInvoiceForClient(@RequestBody InvoiceRequestDto invoice,
                                                          @PathVariable("clientId") String clientId) {
        try {
            Optional<Client> client = clientServiceImpl.findClientById(clientId);
            Optional<Product> product = productService.findProductById(invoice.getProductId());
            if (client.isPresent() && product.isPresent()) {
                Optional<Invoice> addedInvoice = invoiceService.appendInvoiceToClient(invoice,client.get(), product.get());
                return new ResponseEntity<>(addedInvoice.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
