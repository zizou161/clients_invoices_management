package com.example.clientcrud.controllers;

import com.example.clientcrud.dto.request.InvoiceItemRequestDto;
import com.example.clientcrud.dto.response.InvoiceItemResponseDto;
import com.example.clientcrud.entities.Invoice;
import com.example.clientcrud.entities.InvoiceItem;
import com.example.clientcrud.entities.InvoiceItemPK;
import com.example.clientcrud.entities.Product;
import com.example.clientcrud.services.InvoiceItemServiceImpl;
import com.example.clientcrud.services.InvoiceServiceImpl;
import com.example.clientcrud.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class InvoiceItemController {

    @Autowired
    InvoiceItemServiceImpl invoiceItemService;
    @Autowired
    InvoiceServiceImpl invoiceService;
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/invoice/{invoiceId}/item")
    public ResponseEntity<Iterable<InvoiceItem>> listItemsOfInvoice(@PathVariable("invoiceId") String invoiceId) {
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

    @GetMapping("/invoice_item/{id}")
    public ResponseEntity<InvoiceItem> getInvoiceItemById(@PathVariable("id") String invoiceItemId) {
        try {
            Optional<InvoiceItem> invoiceItem = invoiceItemService.findInvoiceItemById(invoiceItemId);
            return invoiceItem
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/invoice_item")
    public ResponseEntity<InvoiceItemResponseDto> createInvoiceItem(@RequestBody InvoiceItemRequestDto invoiceItem) {
        try {
            Optional<Invoice> invoice = invoiceService.findInvoiceById(invoiceItem.getInvoiceId());
            Optional<Product> product = productService.findProductById(invoiceItem.getProductId());
            if (invoice.isPresent() && product.isPresent()) {
                InvoiceItem invoiceItemSaved = invoiceItemService.appendInvoiceItem(invoice.get(), product.get(), invoiceItem.getQuantity());
                InvoiceItemResponseDto invoiceItemResponse = new InvoiceItemResponseDto(invoiceItemSaved);
                return new ResponseEntity<>(invoiceItemResponse, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
