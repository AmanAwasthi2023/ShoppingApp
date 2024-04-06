package com.project.datavisualization.dataLoader;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.datavisualization.model.Transaction;
import com.project.datavisualization.repository.TransactionRepository;

import jakarta.annotation.PostConstruct;

@Component
public class TransactionDataLoader {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostConstruct
    public void init() {
        Transaction transaction1 = new Transaction();
        transaction1.setUserId(1L);
        transaction1.setOrderId(1L);
        transaction1.setTransactionId("tran1");
        transaction1.setStatus("Successful");
        transaction1.setDescription("Payment successful");
        transaction1.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction1);

        Transaction transaction2 = new Transaction();
        transaction2.setUserId(2L);
        transaction2.setOrderId(2L);
        transaction2.setTransactionId("tran2");
        transaction2.setStatus("Failed");
        transaction2.setDescription("Payment failed");
        transaction2.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction2);
    }
}
