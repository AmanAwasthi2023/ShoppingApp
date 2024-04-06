package com.project.datavisualization.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transaction 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    
    private Long orderId;
    
    private String transactionId;
    
    private String status;
    
    private String description;
    
    private LocalDateTime transactionDate;
    
    public Transaction() {
    }

    public Transaction(String message) {
        this.status = message;
    }
}
