package com.example.clientcrud.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity @Getter @Setter
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    private String name;
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    List<ProductInvoice> productInvoices = new ArrayList<>();
}
