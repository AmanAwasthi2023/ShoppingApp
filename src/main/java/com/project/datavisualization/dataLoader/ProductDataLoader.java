package com.project.datavisualization.dataLoader;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.datavisualization.model.Product;
import com.project.datavisualization.repository.ProductRepository;

import jakarta.annotation.PostConstruct;

@Component
public class ProductDataLoader {

    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    public void init() {
        // Create and save sample products to the database
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(100);
        product1.setAvailableQuantity(50);
        product1.setOrdered(0);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(200);
        product2.setAvailableQuantity(100);
        product2.setOrdered(0);
        productRepository.save(product2);
    }
}

