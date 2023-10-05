package com.example.clientcrud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvoiceItem {

    public InvoiceItem(Invoice invoice, Product product, long quantity) {
        this.setInvoice(invoice);
        this.setProduct(product);
        this.quantity = quantity;
    }

    public InvoiceItem() {}

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice")
    private Invoice invoice;

    private long quantity;

}
