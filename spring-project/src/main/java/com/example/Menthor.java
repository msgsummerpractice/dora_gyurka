package com.example;

import org.springframework.stereotype.Component;

@Component("menthor")
public class Menthor implements Employee {

    @Override
    public void display() {
        System.out.println("Hello from Menthor");
    }
    
}
