package com.example.spring_jpa.service;
import java.security.SecureRandom;
import com.example.spring_jpa.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.spring_jpa.dto.SignInRequest;
import com.example.spring_jpa.providers.JWTokenProvider;
import com.example.spring_jpa.repository.TokenRepository;
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
        return "OTP sent to user: " + otp;
    }

    @Override
    public String verifyOtp(String username, String otp) {
       UserDetails user = userDetailService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        
        Token token = tokenRepository.findByUsernameAndUsedFalse(username)
                .orElseThrow(() -> new RuntimeException("No valid OTP found for user"));

        if (token.getExpiresAt().isBefore(java.time.LocalDateTime.now())) {
            throw new RuntimeException("OTP has expired");
        }

        if (token.getToken().equals(otp)) {
            token.setUsed(true);
            tokenRepository.save(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtTokenProvider.generateToken(authentication);
            return jwtToken;
        } else {
            throw new RuntimeException("Invalid OTP");
        }
        
    }
}
