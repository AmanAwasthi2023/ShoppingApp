package com.project.datavisualization.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.datavisualization.service.ProductService;

@RestController
@RequestMapping("/inventory")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Map<String, Integer>>> getInventory() {
        List<Map<String, Integer>> inventoryList = productService.getInventory();
        return ResponseEntity.ok(inventoryList);
    }

}
