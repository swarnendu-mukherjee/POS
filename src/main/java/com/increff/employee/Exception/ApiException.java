package com.increff.employee.Exception;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}

