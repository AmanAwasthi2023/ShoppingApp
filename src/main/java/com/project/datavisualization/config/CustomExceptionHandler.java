package com.project.datavisualization.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.datavisualization.exception.InvalidCouponException;
import com.project.datavisualization.exception.InvalidOrderIdException;
import com.project.datavisualization.exception.InvalidQuantityException;
import com.project.datavisualization.exception.NoResponseFromPaymentServerException;
import com.project.datavisualization.exception.OrderAlreadyPaidException;
import com.project.datavisualization.exception.OrderNotFoundException;
import com.project.datavisualization.exception.PaymentFailedException;

public class CustomExceptionHandler 
{
	 @ExceptionHandler({InvalidQuantityException.class, InvalidCouponException.class})
	    public ResponseEntity<String> handleInvalidInputException(Exception ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }

	    @ExceptionHandler({PaymentFailedException.class, InvalidOrderIdException.class, NoResponseFromPaymentServerException.class, OrderAlreadyPaidException.class})
	    public ResponseEntity<String> handlePaymentException(Exception ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	    }

	    @ExceptionHandler(OrderNotFoundException.class)
	    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	    
	    
}
