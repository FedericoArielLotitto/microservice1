package com.federicoariellotitto.microservices.product.service;

import com.federicoariellotitto.microservices.product.dto.ProductRequest;
import com.federicoariellotitto.microservices.product.model.Product;
import com.federicoariellotitto.microservices.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        Product productSaved = productRepository.save(product);
        log.info("Product created successfully");
        return productSaved;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
