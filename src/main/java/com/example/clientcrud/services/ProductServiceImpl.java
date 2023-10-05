package com.example.clientcrud.services;

import com.example.clientcrud.dto.request.ProductRequestDto;
import com.example.clientcrud.dto.response.ProductResponseDto;
import com.example.clientcrud.entities.Product;
import com.example.clientcrud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(ProductRequestDto product) {
        Product productEntity = new Product();
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        return productRepository.save(productEntity);
    }

    @Override
    public Iterator<ProductResponseDto> findAllProducts() {
        Iterable<Product> products = productRepository.findAll();
        Stream<ProductResponseDto> productsStream = StreamSupport.stream(products.spliterator(), true).map(ProductResponseDto::new);
        return productsStream.iterator();
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
