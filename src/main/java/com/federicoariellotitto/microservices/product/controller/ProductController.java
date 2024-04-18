package com.federicoariellotitto.microservices.product.controller;

import com.federicoariellotitto.microservices.product.dto.ProductRequest;
import com.federicoariellotitto.microservices.product.dto.ProductResponse;
import com.federicoariellotitto.microservices.product.model.Product;
import com.federicoariellotitto.microservices.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        // I don't entirely agree with this part
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        Product productSaved = productService.createProduct(product);
        return new ProductResponse(
                productSaved.getId(),
                productSaved.getName(),
                productSaved.getDescription(),
                productSaved.getPrice()
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                ))
                .collect(Collectors.toList());
    }
}
