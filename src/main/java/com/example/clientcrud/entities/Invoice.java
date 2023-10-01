package com.example.clientcrud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity @Getter @Setter
public class Invoice {
    @Id @GeneratedValue
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    private Date date;
    @JsonIgnore
    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "invoice")
    private List<ProductInvoice> productInvoices = new ArrayList<>();

}
