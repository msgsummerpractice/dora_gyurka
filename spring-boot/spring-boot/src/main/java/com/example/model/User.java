package com.example.model;

import org.springframework.stereotype.Component;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Component
public class User {
    
    @NotBlank
    private String name;

    @Min(value=0)
    int age;

    @NotBlank
    @NotEmpty
    @Size(max=20)
    private String email;

    public User() {
    }
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
