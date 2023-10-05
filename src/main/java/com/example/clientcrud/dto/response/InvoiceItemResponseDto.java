package com.example.clientcrud.dto.response;

import com.example.clientcrud.entities.InvoiceItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InvoiceItemResponseDto {

    private String invoiceId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private long quantity;

    public InvoiceItemResponseDto(){}
    public InvoiceItemResponseDto(InvoiceItem invoiceItem) {
        this.invoiceId = invoiceItem.getInvoice().getId();
        this.productId = invoiceItem.getProduct().getUuid();
        this.productPrice = invoiceItem.getProduct().getPrice();
        this.productName = invoiceItem.getProduct().getName();
        this.quantity = invoiceItem.getQuantity();
    }

}
