package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Greetings {
    private final Employee employee;

    @Autowired
    public Greetings(@Qualifier("menthor") Employee employee) {
        this.employee = employee;
    }

    public void display() {
        employee.display();
    }
}

