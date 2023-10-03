package com.example.clientcrud.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class InvoiceItem {

    @EmbeddedId
    private InvoiceItemPK invoiceItemPK;
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;


    @ManyToOne
    @JoinColumn(name = "invoice")
    private Invoice invoice;

    private long quantity;

}
