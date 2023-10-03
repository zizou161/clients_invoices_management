package com.example.clientcrud.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class InvoiceItemPK implements Serializable {
    private String productId;
    private String invoiceId;
}
