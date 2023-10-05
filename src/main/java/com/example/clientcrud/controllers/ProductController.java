package com.example.clientcrud.controllers;

import com.example.clientcrud.dto.request.ProductRequestDto;
import com.example.clientcrud.dto.response.ProductResponseDto;
import com.example.clientcrud.entities.Product;
import com.example.clientcrud.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/product")
    public ResponseEntity<Iterator<ProductResponseDto>> getProductsList() {
        try {
            Iterator<ProductResponseDto> products = productService.findAllProducts();
            if (products.hasNext()) {
                return new ResponseEntity<>(products, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        try {
            Optional<Product> product = productService.findProductById(id);
            return product
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDto product) {
        try {
            Product productCreated = productService.saveProduct(product);
            return new ResponseEntity<>(productCreated, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> modifyProduct(@PathVariable("id") String id,
                                                 @RequestBody Product product) {
        try {
            Product newProductVals = productService.updateProduct(product, id);
            if (newProductVals == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(newProductVals, HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
