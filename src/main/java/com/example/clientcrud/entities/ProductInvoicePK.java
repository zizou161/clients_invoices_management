package com.example.clientcrud.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable @Getter @Setter
public class ProductInvoicePK implements Serializable {
    private Long productId;
    private Long invoiceId;
}
