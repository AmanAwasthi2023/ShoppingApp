package com.project.datavisualization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.datavisualization.model.Transaction;
import com.project.datavisualization.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getUserTransactions(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public Transaction getOrderTransaction(Long userId, Long orderId) {
        return transactionRepository.findByUserIdAndOrderId(userId, orderId);
    }
}

