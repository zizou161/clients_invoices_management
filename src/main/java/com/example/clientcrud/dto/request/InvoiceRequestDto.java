package com.example.clientcrud.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
public class InvoiceRequestDto {
    @NotNull
    private Date date;
    @NotNull
    private String productId;
    @NotNull
    private long quantity;
}
