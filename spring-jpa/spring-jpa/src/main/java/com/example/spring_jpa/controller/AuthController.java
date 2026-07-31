package com.example.spring_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.spring_jpa.dto.SignInRequest;
import com.example.spring_jpa.dto.SignInResponse;
import com.example.spring_jpa.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<SignInResponse> authenticateUser(@RequestBody SignInRequest signInRequest) {
        
        String token = authService.login(signInRequest);
        System.out.println("Token: " + token);
        SignInResponse response = new SignInResponse();
        response.setAccessToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
