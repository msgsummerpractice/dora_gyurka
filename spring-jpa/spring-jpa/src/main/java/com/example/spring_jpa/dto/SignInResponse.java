package com.example.spring_jpa.dto;
import java.util.List;

import com.example.spring_jpa.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {
    
    private String token;
    private List<Role> roles;
}
