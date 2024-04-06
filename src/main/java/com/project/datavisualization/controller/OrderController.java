package com.project.datavisualization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.datavisualization.exception.InvalidCouponException;
import com.project.datavisualization.exception.InvalidOrderIdException;
import com.project.datavisualization.exception.InvalidQuantityException;
import com.project.datavisualization.exception.NoResponseFromPaymentServerException;
import com.project.datavisualization.exception.OrderAlreadyPaidException;
import com.project.datavisualization.exception.OrderNotFoundException;
import com.project.datavisualization.exception.PaymentFailedException;
import com.project.datavisualization.model.PurchaseOrder;
import com.project.datavisualization.model.Transaction;
import com.project.datavisualization.service.OrderService;

@RestController
@RequestMapping("/purchase")
public class OrderController 
{
	@Autowired
    private OrderService orderService;
	
	@PostMapping("/{userId}/order")
	public ResponseEntity<PurchaseOrder> placeOrder(@PathVariable Long userId,
	                                                 @RequestParam int qty,
	                                                 @RequestParam String coupon) {
	    try {
	        PurchaseOrder orderResponse = orderService.placeOrder(userId, qty, coupon);
	        return ResponseEntity.ok(orderResponse);
	    } catch (InvalidQuantityException | InvalidCouponException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PurchaseOrder(e.getMessage()));
	    }
	}

	@PostMapping("/{userId}/{orderId}/pay")
	public ResponseEntity<Transaction> makePayment(@PathVariable Long userId,
	                                                @PathVariable Long orderId,
	                                                @RequestParam double amount) {
	    try {
	        Transaction transactionResponse = orderService.makePayment(userId, orderId, amount);
	        return ResponseEntity.ok(transactionResponse);
	    } catch (PaymentFailedException | InvalidOrderIdException | NoResponseFromPaymentServerException | OrderAlreadyPaidException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Transaction(e.getMessage()));
	    }
	}

	 
	 
	 @GetMapping("/{userId}/orders")
	    public ResponseEntity<List<PurchaseOrder>> getAllOrders(@PathVariable Long userId) {
	        List<PurchaseOrder> orders = orderService.getAllOrders(userId);
	        return ResponseEntity.ok(orders);
	    }

	    @GetMapping("/{userId}/orders/{orderId}")
	    public ResponseEntity<List<Transaction>> getOrderTransactions(@PathVariable Long userId,
	                                                                   @PathVariable Long orderId) {
	        try {
	            List<Transaction> transactions = orderService.getOrderTransactions(userId, orderId);
	            return ResponseEntity.ok(transactions);
	        } catch (OrderNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
}
