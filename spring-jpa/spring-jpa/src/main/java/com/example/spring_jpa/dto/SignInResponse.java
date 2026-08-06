package com.example.spring_jpa.dto;

import java.util.Set;

import com.example.spring_jpa.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {

  private String accessToken;
  private Set<Role> roles;
}
