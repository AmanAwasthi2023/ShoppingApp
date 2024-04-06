package com.project.datavisualization.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private int price;
    
    private int availableQuantity;
    
    private int ordered;
    
    
    static {
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(100);
        product1.setAvailableQuantity(50);
        product1.setOrdered(0);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(200);
        product2.setAvailableQuantity(100);
        product2.setOrdered(0);
    }
}
