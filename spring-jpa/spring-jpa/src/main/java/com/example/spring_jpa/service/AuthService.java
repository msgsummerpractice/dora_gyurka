package com.example.spring_jpa.service;
import com.example.spring_jpa.dto.SignInRequest;
public interface AuthService {

    String login(SignInRequest signInRequest);
}
