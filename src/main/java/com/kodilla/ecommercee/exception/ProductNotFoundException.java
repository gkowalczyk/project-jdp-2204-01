package com.kodilla.ecommercee.exception;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException() {

    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
