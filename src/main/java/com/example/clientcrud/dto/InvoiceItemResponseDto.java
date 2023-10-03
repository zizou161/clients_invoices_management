package com.example.clientcrud.dto;

import com.example.clientcrud.entities.Invoice;
import com.example.clientcrud.entities.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceItemResponseDto {

    private Product product;
    private Invoice invoice;
    private long quantity;

}
