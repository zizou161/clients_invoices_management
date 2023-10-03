package com.example.clientcrud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Invoice {
    @Id
    private String uuid = UUID.randomUUID().toString();
    @Past
    private Date date;
    @NotNull
    @JsonIgnore
    @ManyToOne
    private Client client;
    @NotEmpty
    @OneToMany(mappedBy = "invoice")
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    public void addInvoiceItem(InvoiceItem invoiceItem) {
        if (this.invoiceItems == null) {
            this.invoiceItems = new ArrayList<>();
        }
        invoiceItem.setInvoice(this);
        this.invoiceItems.add(invoiceItem);
    }

}
