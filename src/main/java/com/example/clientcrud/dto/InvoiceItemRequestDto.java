package com.example.clientcrud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceItemRequestDto {

    private Long productId;
    private long invoiceId;
    private long quantity;
}
