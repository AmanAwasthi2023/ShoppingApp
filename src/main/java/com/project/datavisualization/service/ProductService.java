package com.project.datavisualization.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.datavisualization.model.Product;
import com.project.datavisualization.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Map<String, Integer>> getInventory() {
        List<Product> products = productRepository.findAll();
        List<Map<String, Integer>> inventoryList = new ArrayList<>();
        
        for (Product product : products) {
            Map<String, Integer> inventory = new HashMap<>();
            inventory.put("ordered", product.getOrdered());
            inventory.put("price", product.getPrice());
            inventory.put("available", product.getAvailableQuantity());
            inventoryList.add(inventory);
        }
        
        return inventoryList;
    }
}
