package com.project.datavisualization.dataLoader;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.datavisualization.model.PurchaseOrder;
import com.project.datavisualization.repository.PurchaseOrderRepository;

import jakarta.annotation.PostConstruct;

@Component
public class PurchaseOrderDataLoader {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @PostConstruct
    public void init() {
        PurchaseOrder order1 = new PurchaseOrder();
        order1.setUserId(1L);
        order1.setQuantity(10);
        order1.setAmount(90);
        order1.setCoupon("OFF5");
        order1.setStatus("Completed");
        order1.setOrderDate(LocalDateTime.now());
        purchaseOrderRepository.save(order1);

        PurchaseOrder order2 = new PurchaseOrder();
        order2.setUserId(2L);
        order2.setQuantity(5);
        order2.setAmount(45);
        order2.setCoupon("OFF10");
        order2.setStatus("Completed");
        order2.setOrderDate(LocalDateTime.now());
        purchaseOrderRepository.save(order2);
    }
}

