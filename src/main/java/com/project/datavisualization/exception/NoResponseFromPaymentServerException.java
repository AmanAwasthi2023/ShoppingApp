package com.project.datavisualization.exception;

public class NoResponseFromPaymentServerException extends RuntimeException {
    public NoResponseFromPaymentServerException(String message) {
        super(message);
    }
}