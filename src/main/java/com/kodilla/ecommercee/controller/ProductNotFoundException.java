package com.kodilla.ecommercee.controller;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException() {

    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
