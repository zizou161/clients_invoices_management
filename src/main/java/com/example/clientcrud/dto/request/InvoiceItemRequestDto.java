package com.example.clientcrud.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceItemRequestDto {

    private String productId;
    private String invoiceId;
    private long quantity;
}
