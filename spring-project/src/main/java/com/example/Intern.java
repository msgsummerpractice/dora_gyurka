package com.example;

import org.springframework.stereotype.Component;

@Component("intern")
public class Intern implements Employee {

    @Override
    public void display() {
      System.out.println("Hello Spring");
    }
    
}
