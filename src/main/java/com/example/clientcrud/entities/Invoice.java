package com.example.clientcrud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter()
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Past
    private Date date;
    @NotNull
    @JsonIgnore
    @ManyToOne
    private Client client;
    @NotEmpty
    @OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    public void addInvoiceItem(InvoiceItem invoiceItem) {
        if (this.invoiceItems == null) {
            this.invoiceItems = new ArrayList<>();
        }
        invoiceItem.setInvoice(this);
        this.invoiceItems.add(invoiceItem);
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
