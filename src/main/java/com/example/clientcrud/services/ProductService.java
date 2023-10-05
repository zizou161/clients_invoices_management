package com.example.clientcrud.services;

import com.example.clientcrud.dto.request.ProductRequestDto;
import com.example.clientcrud.dto.response.ProductResponseDto;
import com.example.clientcrud.entities.Product;

import java.util.Iterator;
import java.util.Optional;

public interface ProductService {

    Product saveProduct(ProductRequestDto product);

    Iterator<ProductResponseDto> findAllProducts();

    Optional<Product> findProductById(String id);

    ProductResponseDto updateProduct(ProductRequestDto product, String id);

    void deleteProduct(String id);

}
