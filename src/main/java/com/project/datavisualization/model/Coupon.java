package com.project.datavisualization.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Coupon 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private int discountPercentage;
    
    static {
        Coupon coupon1 = new Coupon();
        coupon1.setCode("OFF5");
        coupon1.setDiscountPercentage(5);

        Coupon coupon2 = new Coupon();
        coupon2.setCode("OFF10");
        coupon2.setDiscountPercentage(10);
    }
}
