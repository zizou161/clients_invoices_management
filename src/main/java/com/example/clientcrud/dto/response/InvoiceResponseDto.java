package com.example.clientcrud.dto.response;

import com.example.clientcrud.entities.Invoice;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class InvoiceResponseDto {
    private String id;
    private String clientId;
    private String clientName;
    private Date date;
    private List<InvoiceItemResponseDto> invoiceItemResponseDto;

    public InvoiceResponseDto(Invoice invoice) {
        this.id = invoice.getId();
        this.clientId = invoice.getClient().getId();
        this.clientName = invoice.getClient().getName();
        this.date = invoice.getDate();
        this.invoiceItemResponseDto = invoice
                .getInvoiceItems()
                .stream()
                .map(InvoiceItemResponseDto::new)
                .collect(Collectors.toList());
    }
}
