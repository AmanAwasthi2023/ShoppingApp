package com.project.datavisualization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.datavisualization.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findByUserId(Long userId);

    Transaction findByUserIdAndOrderId(Long userId, Long orderId);
    
    List<Transaction> findAllByUserIdAndOrderId(Long userId, Long orderId);
}