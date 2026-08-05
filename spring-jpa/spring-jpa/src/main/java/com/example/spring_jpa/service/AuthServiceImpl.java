package com.example.spring_jpa.service;
import java.security.SecureRandom;
import com.example.spring_jpa.model.Token;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.spring_jpa.dto.SignInRequest;
import com.example.spring_jpa.providers.JWTokenProvider;
import com.example.spring_jpa.repository.TokenRepository;

import org.slf4j.Logger;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserDetailService userDetailService;

    private SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private JWTokenProvider jwtTokenProvider;

    @Override
    public String login(SignInRequest signInRequest) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //String token = jwtTokenProvider.generateToken(authentication);
        int otp = 100000 + secureRandom.nextInt(900000); 
        tokenRepository.save(new Token(null, signInRequest.getUsername(), String.valueOf(otp), java.time.LocalDateTime.now().plusMinutes(5),false,null));
        Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
        logger.info("OTP sent to user: " + otp);
        return "OTP sent to user";
    }

    @Override
public String verifyOtp(String username, String otp) {

    System.out.println("Checking OTP for: " + username);
    System.out.println("Received OTP: " + otp);

    Token token = tokenRepository.findByUsernameAndUsedFalse(username)
            .orElse(null);

    if (token == null) {
        System.out.println("No OTP found");
        return null;
    }

    System.out.println("Stored OTP: " + token.getToken());
    System.out.println("Expires: " + token.getExpiresAt());

    if (token.getExpiresAt().isBefore(java.time.LocalDateTime.now())) {
        System.out.println("OTP expired");
        return null;
    }

    if (!token.getToken().equals(otp)) {
        System.out.println("OTP mismatch");
        return null;
    }

    token.setUsed(true);
    tokenRepository.save(token);

    UserDetails user = userDetailService.loadUserByUsername(username);

    Authentication authentication =
            new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return jwtTokenProvider.generateToken(authentication);
}
}
