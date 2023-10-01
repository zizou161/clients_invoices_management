package com.example.clientcrud.controllers;

import com.example.clientcrud.entities.Product;
import com.example.clientcrud.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/product")
    public ResponseEntity<Iterable<Product>> getProductsList() {
        try {
            Iterable<Product> products = productService.findAllProducts();
            if (products.iterator().hasNext()) {
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product productCreated = productService.saveProduct(product);
            return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
