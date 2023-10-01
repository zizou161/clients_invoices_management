package com.example.clientcrud.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
public class Client {

    @Id
    @GeneratedValue
    private Long id;
    private String uuid = UUID.randomUUID().toString();
    private String name;
    private String address;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Invoice> invoices = new ArrayList<>();

    public void addInvoice(Invoice invoice) {
        if (this.invoices == null) {
            invoices = new ArrayList<>();

        }
        invoice.setClient(this);
        this.invoices.add(invoice);
    }
}
