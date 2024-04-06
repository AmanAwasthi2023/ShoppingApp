package com.project.datavisualization.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "purchase_order")
public class PurchaseOrder 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    
    private int quantity;
    
    private double amount;
    
    private String coupon;
    
    private String status;
    
    private LocalDateTime orderDate;
    
    public PurchaseOrder() {
    }

    // Constructor with a String parameter for error messages
    public PurchaseOrder(String message) {
        this.status = message;
    }
    
}
