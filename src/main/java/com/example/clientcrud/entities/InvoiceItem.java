package com.example.clientcrud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class InvoiceItem {

    public InvoiceItem(Invoice invoice, Product product, long quantity) {
        InvoiceItemPK pk = new InvoiceItemPK();
        pk.setProductId(product.getId());
        pk.setInvoiceId(invoice.getId());
        this.invoiceItemPK = pk;
        this.setInvoice(invoice);
        this.setProduct(product);
        this.quantity = quantity;

    }

    public InvoiceItem() {}

    @EmbeddedId
    private InvoiceItemPK invoiceItemPK;
    private String uuid = UUID.randomUUID().toString();

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
