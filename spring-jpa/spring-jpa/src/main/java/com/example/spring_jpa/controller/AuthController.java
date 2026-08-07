package com.example.spring_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_jpa.dto.OtpResponse;
import com.example.spring_jpa.dto.SignInRequest;
import com.example.spring_jpa.dto.SignInResponse;
import com.example.spring_jpa.service.AuthService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins ="https://thankful-bush-057c86c03.7.azurestaticapps.net")
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<SignInResponse> authenticateUser(@RequestBody SignInRequest signInRequest) {
        String token = authService.login(signInRequest);
        SignInResponse response = new SignInResponse();
        response.setAccessToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<SignInResponse> verifyOtp(@RequestBody OtpResponse otpResponse) {
        String jwtToken = authService.verifyOtp(otpResponse.getUsername(), otpResponse.getOtp());
        if (jwtToken != null) {
            SignInResponse response = new SignInResponse();
            response.setAccessToken(jwtToken);
            log.info("Your token here:" + jwtToken);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
