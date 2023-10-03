package com.example.clientcrud.repositories;

import com.example.clientcrud.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
