package com.example.clientcrud.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    private String name;
    @Positive
    private BigDecimal price;
    @OneToMany(mappedBy = "product")
    List<InvoiceItem> invoiceItems = new ArrayList<>();

    public void addInvoiceItem(InvoiceItem invoiceItem) {
        if (this.invoiceItems == null) {
            this.invoiceItems = new ArrayList<>();
        }
        invoiceItem.setProduct(this);
        this.invoiceItems.add(invoiceItem);
    }
}
