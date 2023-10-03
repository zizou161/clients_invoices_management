package com.example.clientcrud.services;

import com.example.clientcrud.entities.Product;
import com.example.clientcrud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Iterable<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Product product, String id) {
        Optional<Product> productDB = findProductById(id);
        if (productDB.isPresent()) {
            Product newProductVals = productDB.get();
            newProductVals.setName(product.getName());
            newProductVals.setPrice(product.getPrice());
            newProductVals.setInvoiceItems(product.getInvoiceItems());
            return productRepository.save(newProductVals);
        } else {
            return null;
        }
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
