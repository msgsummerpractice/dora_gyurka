package com.example.spring_jpa.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.spring_jpa.model.User;
import com.example.spring_jpa.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User uesr = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

       return org.springframework.security.core.userdetails.User.builder()
                .username(uesr.getUsername())
                .password(uesr.getPassword())
                .roles(uesr.getRole().getRole())
                .build();
    }

    public String createUser(String username, String password) {
        com.example.spring_jpa.model.User user = (com.example.spring_jpa.model.User) org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .roles("USER")
                .build();

        userRepository.save(user);
        return "User created successfully";
    }
}
