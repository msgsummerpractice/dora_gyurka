package com.example.spring_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_jpa.dto.OtpResponse;
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
        System.out.println("LOGIN CONTROLLER CALLED");
        String token = authService.login(signInRequest);
        SignInResponse response = new SignInResponse();
        response.setAccessToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<SignInResponse> verifyOtp(@RequestBody OtpResponse otpResponse) {
        System.out.println("Username: " + otpResponse.getUsername());
    System.out.println("OTP: " + otpResponse.getOtp()); 
        String jwtToken = authService.verifyOtp(otpResponse.getUsername(), otpResponse.getOtp());
         System.out.println("JWT result: " + jwtToken);
        if (jwtToken != null) {
            SignInResponse response = new SignInResponse();
            response.setAccessToken(jwtToken);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
