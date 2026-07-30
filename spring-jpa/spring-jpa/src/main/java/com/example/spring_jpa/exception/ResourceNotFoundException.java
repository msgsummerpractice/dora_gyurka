package com.example.spring_jpa.exception;

public class ResourceNotFoundException extends RuntimeException  {

    public ResourceNotFoundException(String message) {
        super(message);
    }
    
}
