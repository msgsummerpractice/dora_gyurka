package com.example.spring_jpa.providers;

import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Base64;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

@Component
public class JWTokenProvider {
    
    private String jwtSecret = "c3VwZXJzZWNyZXRzdXBlcnNlY3JldHN1cGVyc2VjcmV0c3VwZXJzZWNyZXQ=";
    private long jwtExpirationDate = 3600000; //1h = 3600s and 3600*1000 = 3600000 milliseconds

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationDate);

        String token =  Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith((SecretKey) key())
                .compact();

        return token;
    }

     public String generateSecretKey() {
        // length means (32 bytes are required for 256-bit key)
        int length = 32; 

        // Create a secure random generator
        SecureRandom secureRandom = new SecureRandom();

        // Create a byte array to hold the random bytes
        byte[] keyBytes = new byte[length];

        // Generate the random bytes
        secureRandom.nextBytes(keyBytes);

        // Encode the key in Base64 format for easier storage and usage
        return Base64.getUrlEncoder().withoutPadding().encodeToString(keyBytes);
    }  

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey)key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

     public boolean validateToken(String token){
        System.out.println("Token: " + token);
        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
        return true;

    }
}
