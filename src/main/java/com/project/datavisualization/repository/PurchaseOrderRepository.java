package com.project.datavisualization.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.datavisualization.model.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{
	
	List<PurchaseOrder> findAllByUserId(Long userId);
    Optional<PurchaseOrder> findByIdAndUserId(Long id, Long userId);

}
