package com.example.clientcrud.dto.response;

import com.example.clientcrud.dto.request.ProductRequestDto;
import com.example.clientcrud.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {
    private String id;
    private String name;
    private BigDecimal price;

    public ProductResponseDto(){}
    public ProductResponseDto(Product product) {
        this.id = product.getUuid();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
