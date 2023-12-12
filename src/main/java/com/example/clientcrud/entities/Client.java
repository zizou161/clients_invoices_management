package com.example.clientcrud.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "client", schema = "public")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank
    private String name;
    private String address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Invoice> invoices = new ArrayList<>();

    public void addInvoice(Invoice invoice) {
        if (this.invoices == null) {
            invoices = new ArrayList<>();
        }
        invoice.setClient(this);
        this.invoices.add(invoice);
    }

    public void addInvoice(List<Invoice> invoices) {
        invoices.forEach(inv -> inv.setClient(this));
        this.invoices.addAll(invoices);
    }
}
