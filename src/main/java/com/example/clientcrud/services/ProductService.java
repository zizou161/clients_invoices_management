package com.example.clientcrud.services;

import com.example.clientcrud.entities.Product;

import java.util.Optional;

public interface ProductService {

    Product saveProduct(Product product);

    Iterable<Product> findAllProducts();

    Optional<Product> findProductById(Long id);

    Product updateProduct(Product product, Long id);

    void deleteProduct(Long id);

}
